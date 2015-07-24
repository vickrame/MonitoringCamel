package com.ujoodha.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ujoodha.common.exception.FunctionalException;
import com.ujoodha.config.CustomCamelContext;
import com.ujoodha.config.routes.CustomRouteBuilder;
import com.ujoodha.config.routes.FileToFileRoute;

/**
 * Servlet implementation class StartCamelRouteServlet
 */
// @WebServlet("/StartCamelRouteServlet")
public class StartCamelRouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomCamelContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartCamelRouteServlet() {
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
		CustomRouteBuilder builderRoute = new FileToFileRoute();
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
		context = new CustomCamelContext("file");
		super.init();
	}

}
