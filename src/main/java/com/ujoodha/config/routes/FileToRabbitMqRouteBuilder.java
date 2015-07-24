/**
 * 
 */
package com.ujoodha.config.routes;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.dataformat.BindyType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujoodha.common.UserLigth;
import com.ujoodha.config.processor.MessageRabbitProcessor;

/**
 * @author vickrame
 *
 */
public class FileToRabbitMqRouteBuilder extends CustomRouteBuilder {

	private String repEntree;

	private String mongoHost = null;
	private String mongoPort = null;
	private String mongoDataBase = null;
	private String mongoCollection = null;

	private String rabbitHost;
	private String rabbitPort;
	private String rabbitDataBase;
	private String rabbitCollection;

	private String rabbitUser;

	private String rabbitPassword;

	private String rabbitExchange;

	private String repOut;
	private String in = "C:/Dev/workspace/Camel-Batch/data/in/csv";
	private String out = "C:/Dev/workspace/Camel-Batch/data/out/json";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ujoodha.config.routes.CustomRouteBuilder#composeRoute()
	 */
	@Override
	protected ProcessorDefinition composeRoute() {

		repOut = out;

		repEntree = in;

		// mongoHost = PropertiesReader.INSTANCE
		// .getPropsFromKey(CamelBatchUtils.MONGO_HOSTNAME);
		// mongoPort = PropertiesReader.INSTANCE
		// .getPropsFromKey(CamelBatchUtils.MONGO_PORT_VALUE);
		// mongoDataBase = PropertiesReader.INSTANCE
		// .getPropsFromKey(CamelBatchUtils.MONGO_DATABSE);
		// mongoCollection = PropertiesReader.INSTANCE
		// .getPropsFromKey(CamelBatchUtils.MONGO_COLLECTION);

		// rabbitMQ.port=5672
		// rabbitMQ.host=localhost
		// rabbitMQ.virtual.host=/
		// rabbitMQ.user=guest
		// rabbitMQ.password=guest
		// rabbitMQ.exchange=testcamel

		rabbitHost = "localhost";
		rabbitPort = "5672";
		rabbitUser = "guest";
		rabbitPassword = "guest";

		rabbitExchange = "testcamel";

		// DBObject query = null;
		// query = (DBObject) JSON.parse("{ \"nom\": \"ujoodha\" }");

		// BasicDBObject query = new BasicDBObject();
		// query.put("nom", "ujoodha");

		ObjectMapper objectMapper = new ObjectMapper();
		// objectMapper
		// .disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
		JacksonDataFormat jackson = new JacksonDataFormat(objectMapper, null);

		// "mongodb:mongoDb" + "?database=" + mongoDataBase
		// + "&collection=" + mongoCollection
		// + "&operation=findAll")

		return from("file://" + repEntree)
				.unmarshal()
				.bindy(BindyType.Csv, UserLigth.class)
				.split(body())
				.marshal(jackson)
				.convertBodyTo(String.class)
				.process(new MessageRabbitProcessor())
				.to("rabbitmq://"
						+ rabbitHost
						+ ":"
						+ rabbitPort
						+ "/"
						+ rabbitExchange
						+ "?connectionFactory=#factoryRabbit&autoDelete=false&autoAck=false&routingKey=testcamelqueue");

	}
}
