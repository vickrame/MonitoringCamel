package com.ujoodha.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.camel.component.ActiveMQComponent;

import com.ujoodha.common.exception.FunctionalException;
import com.ujoodha.config.CustomCamelContext;
import com.ujoodha.config.routes.CustomRouteBuilder;
import com.ujoodha.config.routes.FileToAMQPRoute;

/**
 * Servlet implementation class StartCamelRouteServlet
 */
// @WebServlet("/StartCamelRouteServlet")
public class StartCamelRouteAMQP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomCamelContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartCamelRouteAMQP() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("appel de la route");
		CustomRouteBuilder builderRoute = new FileToAMQPRoute();
		try {
			context.doJob(builderRoute);
		} catch (FunctionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("initialisation");
		context = new CustomCamelContext("AMQP");
		ActiveMQComponent component = new ActiveMQComponent();

		component.setBrokerURL("tcp://localhost:61616");
		component.setUserName("admin");
		component.setPassword("admin");

		context.addComponent("activemq", component);

		super.init();
	}

}
