package com.ujoodha.config.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.ujoodha.config.processor.AbstractProcessor;

public class FileToFileRoute extends CustomRouteBuilder {

	private String in = "C:/Dev/workspace/Camel-Batch/data/in/csv";
	private String out = "C:/Dev/workspace/Camel-Batch/data/out/json";

	@Override
	protected RouteDefinition composeRoute() {
		// in = PropertiesReader.INSTANCE
		// .getPropsFromKey(CamelBatchUtils.DIRECTORY_IN);
		// out = PropertiesReader.INSTANCE
		// .getPropsFromKey(CamelBatchUtils.DIRECTORY_OUT);

		processFin = new AbstractProcessor();

		RouteDefinition composeRoute = null;

		composeRoute = from("file://" + in).convertBodyTo(String.class)
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						System.err.println(exchange.getIn().getBody());

					}
				}).unmarshal(buildDataCSV()).marshal().json(JsonLibrary.Gson)
				.to("file://" + out);

		return composeRoute;
	}

}
