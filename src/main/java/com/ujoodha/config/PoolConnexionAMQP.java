/**
 * 
 */
package com.ujoodha.config;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.naming.Context;

import nf.fr.eraasoft.pool.ObjectPool;
import nf.fr.eraasoft.pool.PoolException;
import nf.fr.eraasoft.pool.PoolSettings;
import nf.fr.eraasoft.pool.PoolableObject;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author vickrame
 *
 */
public enum PoolConnexionAMQP {

	INSTANCE;

	private PoolSettings<QueueConnection> poolSettings;

	private PoolConnexionAMQP() {
		poolSettings = new PoolSettings<QueueConnection>(
				new PoolableObject<QueueConnection>() {

					private QueueConnection connection;

					@Override
					public QueueConnection make() throws PoolException {

						Properties props = new Properties();
						props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
								"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
						props.setProperty(Context.PROVIDER_URL,
								"tcp://localhost:61616");
						// javax.naming.Context ctx = new InitialContext(props);

						ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
						factory.buildFromProperties(props);

						// ActiveMQConnectionFactory factory = new
						// ActiveMQConnectionFactory();
						// factory.setsetUsername("admin");
						// factory.setPassword(PropertiesReader.INSTANCE
						// .getPropsFromKey(CamelBatchUtils.RABBIT_PASSWORD));
						// factory.setVirtualHost(PropertiesReader.INSTANCE
						// .getPropsFromKey(CamelBatchUtils.RABBIT_VIRTUALHOST));
						// factory.setHost(PropertiesReader.INSTANCE
						// .getPropsFromKey(CamelBatchUtils.RABBIT_HOSTNAME));
						// factory.setPort(Integer.parseInt(PropertiesReader.INSTANCE
						// .getPropsFromKey(CamelBatchUtils.RABBIT_PORT)));

						// try {
						try {
							connection = factory.createQueueConnection("admin",
									"admin");
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// client = factory.newConnection();
						// } catch (IOException | TimeoutException e) {
						// throw new RuntimeException(
						// "Impossible de construire le pool");
						// }

						return connection;
					}

					@Override
					public boolean validate(QueueConnection t) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void destroy(QueueConnection t) {
					}

					@Override
					public void activate(QueueConnection t)
							throws PoolException {
					}

					@Override
					public void passivate(QueueConnection t) {
					}
				});

		poolSettings.min(0).max(10);
	}

	/**
	 * @return the poolSettings
	 */
	public PoolSettings<QueueConnection> getPoolSettings() {
		return poolSettings;
	}

	public QueueConnection getClient() {
		ObjectPool<QueueConnection> objectPool = poolSettings.pool();

		QueueConnection client = null;
		try {
			client = objectPool.getObj();
			System.out.println("Un client " + client);
		} catch (PoolException e) {
			e.printStackTrace();
		}

		return client;
	}

	public void releaseClient(final QueueConnection client) {
		ObjectPool<QueueConnection> objectPool = poolSettings.pool();
		objectPool.returnObj(client);
	}

}
