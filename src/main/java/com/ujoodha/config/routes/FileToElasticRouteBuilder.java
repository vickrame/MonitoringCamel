//package com.ujoodha.config.routes;
//
//import org.apache.camel.component.jackson.JacksonDataFormat;
//import org.apache.camel.model.ProcessorDefinition;
//import org.apache.camel.model.dataformat.BindyType;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ujoodha.common.UserLigth;
//import com.ujoodha.utils.CamelBatchUtils;
//import com.ujoodha.utils.PropertiesReader;
//
//public class FileToElasticRouteBuilder extends CustomRouteBuilder {
//
//	private String repEntree;
//	private String esCluster;
//	private String esPort;
//	private String esHost;
//	private String esIndex;
//	private String esTypeIndex;
//
//	private ProcessorDefinition routeCompose;
//
//	@Override
//	protected ProcessorDefinition composeRoute() {
//
//		repEntree = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.DIRECTORY_IN);
//
//		esCluster = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.ES_CLUSTER_NAME);
//
//		esPort = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.ES_PORT);
//		esHost = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.ES_HOST);
//		esIndex = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.ES_INDEX_NAME);
//		esTypeIndex = PropertiesReader.INSTANCE
//				.getPropsFromKey(CamelBatchUtils.ES_TYPE_INDEX_NAME);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		// objectMapper
//		// .disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
//		JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, null);
//
//		routeCompose = from("file://" + repEntree)
//				.unmarshal()
//				.bindy(BindyType.Csv, UserLigth.class)
//
//				// // .split(body(UserLigth.class))
//				// // .parallelProcessing()
//
//				// .convertBodyTo(String.class)
//				// .json()
//				.split(body())
//				.marshal(jackson)
//				.to("elasticsearch://" + "elastic"
//						+ "?operation=INDEX&ip=127.0.0.1&port=9300&indexName="
//						+ esIndex + "&indexType=" + esTypeIndex)
//				// from()
//				// .to("elasticsearch://elastic?operation=INDEX&indexName=twitter&indexType=tweet")
//				.process(traceProcess()).end();
//
//		return routeCompose;
//	}
//
//	public void test() {
//
//	}
//
//	// /*
//	// * (non-Javadoc)
//	// *
//	// * @see com.ujoodha.config.routes.CustomRouteBuilder#configure()
//	// */
//	// @Override
//	// public void configure() throws Exception {
//	//
//	// }
// }
