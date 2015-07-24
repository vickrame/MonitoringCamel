//package com.ujoodha.config.routes;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.component.jackson.JacksonDataFormat;
//import org.apache.camel.model.RouteDefinition;
//import org.apache.camel.model.dataformat.BindyType;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ujoodha.common.UserLigth;
//import com.ujoodha.config.processor.ProcessConvert;
//import com.ujoodha.utils.CamelBatchUtils;
//import com.ujoodha.utils.PropertiesReader;
//
//public class FileToMongoRouteBuilder extends CustomRouteBuilder {
//
//	private String repEntree = null;
//
//	private String mongoHost = null;
//	private String mongoPort = null;
//	private String mongoDataBase = null;
//	private String mongoCollection = null;
//
//	@Override
//	protected RouteDefinition composeRoute() {
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
//		RouteDefinition routeCompose = null;
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		// objectMapper
//		// .disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
//		JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, null);
//
//		routeCompose = from("file://" + repEntree)
//				.convertBodyTo(String.class)
//				.process(new Processor() {
//
//					@Override
//					public void process(Exchange exchange) throws Exception {
//						System.err.println(exchange.getIn().getBody());
//
//					}
//				})
//				.unmarshal()
//				.bindy(BindyType.Csv, UserLigth.class)
//				// .marshal(jackson)
//				// .bean(UserLigth.class)
//				.process(new ProcessConvert())
//				.to("mongodb:mongoDb" + "?database=" + mongoDataBase
//						+ "&collection=" + mongoCollection
//						+ "&operation=insert");
//
//		// mongodb:mongoDb
//		return routeCompose;
//
//	}
// }
