package fr.enseirb.it310.projects.jee.ft.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import fr.enseirb.it310.projects.jee.ft.api.FeatureRepository;
import fr.enseirb.it310.projects.jee.ft.model.Feature;

public class ClientApp {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		
		// see https://docs.jboss.org/author/display/AS71/Remote+EJB+invocations+via+JNDI+-+EJB+client+API+or+remote-naming+project
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL,"remote://localhost:4447");
		props.put(Context.SECURITY_PRINCIPAL, "user");
		props.put(Context.SECURITY_CREDENTIALS, "u");
		props.put("jboss.naming.client.ejb.context", true);

		InitialContext ic = new InitialContext(props);
		FeatureRepository r = (FeatureRepository) ic
				.lookup("feature-toggles-app/feature-toggles-ejb/FeatureRepositoryService!fr.enseirb.it310.projects.jee.ft.api.FeatureRepository");

		Feature f = new Feature("feature.one");
		System.out.println("adding feature: " + f.getKey());
		r.registerFeature(f);
		System.out.println("listing features:");
		System.out.println(r.listFeatures());
		System.out.println("removing feature: " + f.getKey());
		r.removeFeature(f);
		System.out.println(r.listFeatures());
	}
}
