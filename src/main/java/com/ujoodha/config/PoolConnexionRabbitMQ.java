/**
 * 
 */
package com.ujoodha.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import nf.fr.eraasoft.pool.ObjectPool;
import nf.fr.eraasoft.pool.PoolException;
import nf.fr.eraasoft.pool.PoolSettings;
import nf.fr.eraasoft.pool.PoolableObject;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author vickrame
 *
 */
public enum PoolConnexionRabbitMQ {

	INSTANCE;

	private PoolSettings<Connection> poolSettings;

	private PoolConnexionRabbitMQ() {
		poolSettings = new PoolSettings<Connection>(
				new PoolableObject<Connection>() {

					private Connection client;

					// rabbitMQ.port=5672
					// rabbitMQ.host=localhost
					// rabbitMQ.virtual.host=/
					// rabbitMQ.user=guest
					// rabbitMQ.password=guest
					// rabbitMQ.exchange=testcamel

					@Override
					public Connection make() throws PoolException {

						ConnectionFactory factory = new ConnectionFactory();
						factory.setUsername("guest");
						factory.setPassword("guest");
						factory.setVirtualHost("/");
						factory.setHost("localhost");
						factory.setPort(5672);

						try {
							client = factory.newConnection();
							System.out.println("client rabbitMQ " + client);
						} catch (IOException ioEx) {

						} catch (TimeoutException e) {
							throw new RuntimeException(
									"Impossible de construire le pool");
						}

						return client;
					}

					@Override
					public boolean validate(Connection t) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void destroy(Connection t) {
					}

					@Override
					public void activate(Connection t) throws PoolException {
					}

					@Override
					public void passivate(Connection t) {
					}
				});

		poolSettings.min(0).max(10);
	}

	/**
	 * @return the poolSettings
	 */
	public PoolSettings<Connection> getPoolSettings() {
		return poolSettings;
	}

	public Connection getClient() throws IOException, TimeoutException {
		ObjectPool<Connection> objectPool = poolSettings.pool();

		Connection client = null;
		try {
			client = objectPool.getObj();

			// Channel channel = client.createChannel();
			//
			// // String exchangeName = "exchangeWithoutRouting";
			// String queueName = "queueRabbitMQ";
			// // String routingKey = "route";
			// boolean durable = true;
			//
			// // channel.exchangeDeclare(exchangeName, "topic", durable); //
			// // declare le nom de l'exchange
			// channel.queueDeclare(queueName, durable, false, false, null); //
			// declare
			// // une
			// // queue
			// // channel.queueBind(queueName, exchangeName, routingKey); //
			// // declaration du routingKey/binidingKey
			//
			// boolean noAck = false;
			//
			// // declaration d'un consommeur
			// QueueingConsumer consumer = new QueueingConsumer(channel);
			// channel.basicConsume(queueName, noAck, consumer);

			// boolean runInfinite = true;
			// while (runInfinite) {
			// QueueingConsumer.Delivery delivery;
			// try {
			// delivery = consumer.nextDelivery();
			// } catch (InterruptedException ie) {
			// continue;
			// }
			// System.out.println("Message recu : "
			// + new String(delivery.getBody()));
			// channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			// }
			// channel.close();
			// conn.close();

			System.out.println("Un client " + client);
		} catch (PoolException e) {
			e.printStackTrace();
		}

		return client;
	}

	public void releaseClient(final Connection client) {
		ObjectPool<Connection> objectPool = poolSettings.pool();
		objectPool.returnObj(client);
	}

}
