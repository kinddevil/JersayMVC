package test.luna.tmm.rosettastone.utils;

import java.io.IOException;

import junit.framework.Assert;
import luna.tmm.rosettastone.utils.RetObject;
import luna.tmm.rosettastone.utils.WebAPI;

import org.apache.http.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WebAPITest {
	
	private WebAPI api;
	
	@Before
	public void setUp(){
		api = new WebAPI();
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void testGet() throws ParseException, IOException {
		api.setIsContext(false);
		RetObject ret = api.call("http://www.baidu.com");
		System.out.println(ret.getStatus() + "--" + ret.getMsg() + "--" + ret.getMsgCode() );
		System.out.println(ret.getData());
		Assert.assertEquals("200", ret.getMsgCode());
		
		/* 
		ret = api.call("http://somewhat.nowhere.page.some");
		System.out.println(ret.getStatus() + "--" + ret.getMsg() + "--" + ret.getMsgCode() );
		System.out.println(ret.getData());
		Assert.assertEquals("0", ret.getStatus());//Some times DNS hacker will cause error
		*/
		
		ret = api.call("https://github.com/sddsafdasdfasdasdfasdff");
		System.out.println(ret.getStatus() + "--" + ret.getMsg() + "--" + ret.getMsgCode() );
		System.out.println(ret.getData());
		Assert.assertEquals("404", ret.getMsgCode());
	}
	
	@Test
	@Ignore
	public void testPut() throws ParseException, IOException {
		api.setIsContext(true);
		RetObject ret = api.callMethod("PUT", "http://localhost:8080/luna/v1/auth/test", null, "a=b&data=d");
		System.out.println(ret.getStatus() + "--" + ret.getMsg() + "--" + ret.getMsgCode() );
		System.out.println(ret.getData());
		Assert.assertEquals("d", ret.getData() ); 
	}
	
}
