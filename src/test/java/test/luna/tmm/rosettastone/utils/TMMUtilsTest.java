package test.luna.tmm.rosettastone.utils;

import luna.tmm.rosettastone.utils.Constants;
import luna.tmm.rosettastone.utils.TMMUtils;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TMMUtilsTest {
	
	protected static ObjectMapper objectMapper;
	
	@Before
	public void setUp(){
		objectMapper = new ObjectMapper();
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void testgetTMMTargetServer() throws JSONException {
		//Test null
		Assert.assertEquals(Constants.TMM_API_URL, TMMUtils.getTMMTargetServer(null));
		//Test Array
		String test = "{\"rsaServer\": [\"ab\", \"cd\"]}";
		JSONObject json = new JSONObject(test);
		Assert.assertEquals("ab", TMMUtils.getTMMTargetServer(json.get("rsaServer")));
		//Test String
		test = "{\"rsaServer\": \"ab\"}";
		json = new JSONObject(test);
		Assert.assertEquals("ab", TMMUtils.getTMMTargetServer(json.get("rsaServer")));
	}
}
