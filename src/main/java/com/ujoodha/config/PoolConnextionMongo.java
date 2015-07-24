//package com.ujoodha.config;
//
//import nf.fr.eraasoft.pool.ObjectPool;
//import nf.fr.eraasoft.pool.PoolException;
//import nf.fr.eraasoft.pool.PoolSettings;
//import nf.fr.eraasoft.pool.PoolableObject;
//
//import com.mongodb.MongoClient;
//import com.ujoodha.utils.CamelBatchUtils;
//import com.ujoodha.utils.PropertiesReader;
//
//public enum PoolConnextionMongo {
//
//	INSTANCE;
//
//	private PoolSettings<MongoClient> poolSettings;
//
//	private PoolConnextionMongo() {
//		poolSettings = new PoolSettings<MongoClient>(
//				new PoolableObject<MongoClient>() {
//
//					private MongoClient client;
//
//					@Override
//					public MongoClient make() throws PoolException {
//						client = new MongoClient(
//								PropertiesReader.INSTANCE
//										.getPropsFromKey(CamelBatchUtils.MONGO_HOSTNAME),
//								Integer.valueOf(PropertiesReader.INSTANCE
//										.getPropsFromKey(CamelBatchUtils.MONGO_PORT_VALUE)));
//						return client;
//					}
//
//					@Override
//					public boolean validate(MongoClient t) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//
//					@Override
//					public void destroy(MongoClient t) {
//					}
//
//					@Override
//					public void activate(MongoClient t) throws PoolException {
//					}
//
//					@Override
//					public void passivate(MongoClient t) {
//					}
//				});
//
//		poolSettings.min(0).max(10);
//	}
//
//	/**
//	 * @return the poolSettings
//	 */
//	public PoolSettings<MongoClient> getPoolSettings() {
//		return poolSettings;
//	}
//
//	public MongoClient getClient() {
//		ObjectPool<MongoClient> objectPool = poolSettings.pool();
//
//		MongoClient client = null;
//		try {
//			client = objectPool.getObj();
//			System.out.println("Un client " + client);
//		} catch (PoolException e) {
//			e.printStackTrace();
//		}
//
//		return client;
//	}
//
//	public void releaseClient(final MongoClient client) {
//		ObjectPool<MongoClient> objectPool = poolSettings.pool();
//		objectPool.returnObj(client);
//	}
//
// }
