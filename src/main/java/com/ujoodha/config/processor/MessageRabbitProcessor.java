/**
 * 
 */
package com.ujoodha.config.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author vickrame
 *
 */
public class MessageRabbitProcessor implements Processor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rabbitmq.EXCHANGE_NAME", "testcamel");
		map.put("rabbitmq.MESSAGE_ID", UUID.randomUUID());

		exchange.getIn().setHeaders(map);

	}

}
