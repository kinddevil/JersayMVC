package luna.tmm.rosettastone.application;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

public class JerseyApp extends ResourceConfig {
	private static Logger logger = LogManager.getLogger("JerseyApp");
	public JerseyApp() {
		logger.log(Level.INFO , "------------Starting Jersay Config...--------------");
//		register(org.glassfish.jersey.server.filter.UriConnegFilter.class);
//        register(org.glassfish.jersey.server.validation.ValidationFeature.class);
//        register(org.glassfish.jersey.server.spring.SpringComponentProvider.class);
//        
        packages("luna.tmm.rosettastone.application");
        register(JspMvcFeature.class);
//        register(luna.tmm.rosettastone.application.filter.RestRequestFilter.class);
        property(JspMvcFeature.TEMPLATES_BASE_PATH, "/WEB-INF/views");
    }
}
