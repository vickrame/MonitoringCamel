///**
// * 
// */
//package com.ujoodha.config.routes;
//
//import org.apache.camel.component.jackson.JacksonDataFormat;
//import org.apache.camel.model.ProcessorDefinition;
//import org.apache.camel.model.dataformat.BindyType;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.ujoodha.common.UserLigth;
//import com.ujoodha.config.processor.MessageRabbitProcessor;
//import com.ujoodha.utils.CamelBatchUtils;
//import com.ujoodha.utils.PropertiesReader;
//
///**
// * @author vickrame
// *
// */
//public class MongoToRabbitMqRouteBuilder extends CustomRouteBuilder {
//
//	private String repEntree;
//
//	private String mongoHost = null;
//	private String mongoPort = null;
//	private String mongoDataBase = null;
//	private String mongoCollection = null;
//
//	private String rabbitHost;
//	private String rabbitPort;
//	private String rabbitDataBase;
//	private String rabbitCollection;
//
//	private String rabbitUser;
//
//	private String rabbitPassword;
//
//	private String rabbitExchange;
//
//	private String repOut;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.ujoodha.config.routes.CustomRouteBuilder#composeRoute()
//	 */
//	@Override
//	protected ProcessorDefinition composeRoute() {
//
//		repOut = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.DIRECTORY_OUT);
//
//		repEntree = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.DIRECTORY_IN);
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
//		rabbitHost = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.RABBIT_HOSTNAME);
//		rabbitPort = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.RABBIT_PORT);
//
//		rabbitUser = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.RABBIT_USER);
//
//		rabbitPassword = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.RABBIT_PASSWORD);
//
//		rabbitExchange = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.RABBIT_EXCHANGE);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		// objectMapper
//		// .disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
//		JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, null);
//
//		// "mongodb:mongoDb" + "?database=" + mongoDataBase
//		// + "&collection=" + mongoCollection
//		// + "&operation=findByCriteria")
//
//		DBObject query = new BasicDBObject();
//		query.put("nom", "ujoodha");
//
//		return
//
//		from("direct:findOneByQuery")
//				.setBody(constant(query))
//				.to("mongodb:mongoDb" + "?database=" + mongoDataBase
//						+ "&collection=" + mongoCollection
//						+ "&operation=findOneByQuery")
//				.unmarshal()
//				.bindy(BindyType.Csv, UserLigth.class)
//				.split(body())
//				.marshal(jackson)
//				.convertBodyTo(String.class)
//				.process(new MessageRabbitProcessor())
//				.to("rabbitmq://"
//						+ rabbitHost
//						+ ":"
//						+ rabbitPort
//						+ "/"
//						+ rabbitExchange
//						+ "?connectionFactory=#factoryRabbit&autoDelete=false&autoAck=false&routingKey=testcamelqueue");
//
//	}
// }
