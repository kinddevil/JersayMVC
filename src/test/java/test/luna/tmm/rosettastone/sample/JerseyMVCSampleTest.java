package test.luna.tmm.rosettastone.sample;

import java.io.IOException;

import javax.ws.rs.core.Application;

import luna.tmm.rosettastone.application.JerseyApp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

public class JerseyMVCSampleTest extends JerseyTest{
	
	@Override
    protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        JerseyApp config = new JerseyApp();
        config.property("contextConfigLocation", "classpath:resources/context-application.xml");
        return config;
    }
	
	@org.junit.Test
	public void test()  {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://localhost:8080/luna/v1/login/fuck");
        try {
			HttpResponse res = httpClient.execute(get);
			System.out.println(res.getStatusLine().getStatusCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
        //assertEquals(200, res.getStatus());
    }
}
