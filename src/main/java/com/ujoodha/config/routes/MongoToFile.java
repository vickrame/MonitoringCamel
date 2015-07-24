///**
// * 
// */
//package com.ujoodha.config.routes;
//
//import org.apache.camel.model.ProcessorDefinition;
//
//import com.mongodb.DBObject;
//import com.mongodb.util.JSON;
//import com.ujoodha.utils.CamelBatchUtils;
//import com.ujoodha.utils.PropertiesReader;
//
///**
// * @author vickrame
// *
// */
//public class MongoToFile extends CustomRouteBuilder {
//
//	private String mongoHost;
//	private String mongoPort;
//	private String mongoCollection;
//	private String mongoDataBase;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.ujoodha.config.routes.CustomRouteBuilder#composeRoute()
//	 */
//	@Override
//	protected ProcessorDefinition composeRoute() {
//
//		mongoHost = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.MONGO_HOSTNAME);
//		mongoPort = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.MONGO_PORT_VALUE);
//		mongoDataBase = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.MONGO_DATABSE);
//		mongoCollection = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.MONGO_COLLECTION);
//
//		String out = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.DIRECTORY_OUT);
//
//		DBObject query = null;
//		query = (DBObject) JSON.parse("{ \"nom\": \"ujoodha\" }");
//
//		return from("direct://superkiki")
//				.setBody(constant(query))
//				.to("mongodb:mongoDb" + "?database=" + mongoDataBase
//						+ "&collection=" + mongoCollection
//						+ "&operation=superkiki").to("file://" + out);
//	}
//
// }
