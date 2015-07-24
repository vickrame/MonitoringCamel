/**
 * 
 */
package com.ujoodha.config.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author vickrame
 *
 */
public class AbstractProcessor implements Processor {

	private boolean jobFinish = false;

	public AbstractProcessor() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("fin du job");

		jobFinish = true;
	}

	/**
	 * @return the jobFinish
	 */
	public boolean isJobFinish() {
		return jobFinish;
	}

}
