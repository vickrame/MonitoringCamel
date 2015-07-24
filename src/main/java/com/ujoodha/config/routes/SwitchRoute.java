/**
 * 
 */
package com.ujoodha.config.routes;

import org.apache.camel.model.ChoiceDefinition;

/**
 * @author vickrame
 *
 */
public class SwitchRoute extends CustomRouteBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ujoodha.config.routes.CustomRouteBuilder#composeRoute()
	 */
	@Override
	protected ChoiceDefinition composeRoute() throws Exception {
		ChoiceDefinition composeRoute = from("file:out?delay=10000").choice()
				.when(xpath("/root/type='A'")).to("jms:mom.tp3B1")
				.when(xpath("/root/type='B'")).to("jms:mom.tp3B2").otherwise()
				.to("jms:mom.tp3.reject");

		return composeRoute;
	}

}
