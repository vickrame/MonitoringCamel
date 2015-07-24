package com.ujoodha.config;

import org.apache.camel.impl.DefaultCamelContext;

import com.ujoodha.common.exception.FunctionalException;
import com.ujoodha.config.routes.CustomRouteBuilder;

/**
 * 
 * @author vickrame
 *
 */
public class CustomCamelContext extends DefaultCamelContext {

	private String name;
	private CustomRouteBuilder routeBuilder;

	public CustomCamelContext(final String nomJob) {
		super();
		name = nomJob;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private void ajouterRoutes(final CustomRouteBuilder route) {
		try {
			System.out.println("On ajoute les routes");
			routeBuilder = route;
			this.addRoutes(routeBuilder);

		} catch (Exception e) {
			throw new RuntimeException("Prb lors de l'ajout des routes");
		}
	}

	public void doJob(final CustomRouteBuilder route)
			throws FunctionalException {

		try {
			ajouterRoutes(route);
			System.out.println("On demarre le job");
			startJob();
			releaseJob();

		} catch (Exception e) {
			e.printStackTrace();
			throw new FunctionalException("01",
					"erreur sur le parsing des routes", e.getCause());
		}

	}

	private void startJob() {
		try {
			this.start();
		} catch (Exception e) {
		}
	}

	private void releaseJob() {

		try {
			while (!routeBuilder.getProcessFin().isJobFinish()) {
				System.out.println("wait " + routeBuilder.getCountError());
				Thread.sleep(2000);
			}
			System.out.println("destroy camel context");
			this.stop();
		} catch (Exception e) {
			System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		}
	}
}
