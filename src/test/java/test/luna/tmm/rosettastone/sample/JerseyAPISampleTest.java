package test.luna.tmm.rosettastone.sample;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import luna.tmm.rosettastone.application.JerseyApp;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;

public class JerseyAPISampleTest extends JerseyTest{
	
	@Override
    protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        JerseyApp config = new JerseyApp();
        config.property("contextConfigLocation", "classpath:resources/context-application.xml");
//        config.property("spring.profiles.active", "development");
        return config;
    }
	
	@Ignore
	@org.junit.Test
	public void test() {
        final String ret = target("login").request().get(String.class);
        //assertEquals(200, res.getStatus());
    }
}
