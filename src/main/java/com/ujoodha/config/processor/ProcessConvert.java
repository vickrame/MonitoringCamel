package com.ujoodha.config.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessConvert implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().getBody();

	}

}
