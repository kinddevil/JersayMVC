package luna.tmm.rosettastone.utils;

import java.io.IOException;
import java.sql.Date;

import luna.tmm.rosettastone.application.resources.Authorization;
import luna.tmm.rosettastone.utils.encryption.HashGeneratorUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TMMUtils {
	
	protected final static Logger logger = LogManager.getLogger(TMMUtils.class);
	protected static ObjectMapper objectMapper = new ObjectMapper();
	private static final String TEST_USER = "p006";
	
	public static String getTMMParamJson(String... params) {
		JSONObject json = new JSONObject();
		Long timestamp = System.currentTimeMillis()/1000;
		String username = getNotNullValue(params[0], TEST_USER);
		try{
			json.put("ptf_cmd", Constants.PTF_CMD);
			json.put("ptf_partner", Constants.PTF_PARTNER);
			json.put("ptf_method", Constants.PTF_METHOD);
			json.put("ptf_timer", timestamp);
			json.put("ptf_check", HashGeneratorUtils.generateMD5(username+Constants.PTF_PARTNER
					+timestamp+Constants.PTF_KEY));
			json.put("ptf_login", username );
			json.put("ptf_endingurl", Constants.PTF_ENDINGURL);
		} catch(JSONException e){
			logger.error(e.getMessage());
		}
		return json.toString();
	}
	
	public static String getTMMParamQuery(String... params) {
		StringBuilder query = new StringBuilder();
		Long timestamp = System.currentTimeMillis()/1000;
		String username = getNotNullValue(params[0], TEST_USER);
		query.append("ptf_cmd").append("=").append(Constants.PTF_CMD).append("&");
		query.append("ptf_partner").append("=").append(Constants.PTF_PARTNER).append("&");
		query.append("ptf_method").append("=").append(Constants.PTF_METHOD).append("&");
		query.append("ptf_timer").append("=").append(timestamp).append("&");
		query.append("ptf_check").append("=").append(HashGeneratorUtils.generateMD5(username+Constants.PTF_PARTNER
				+timestamp+Constants.PTF_KEY)).append("&");
		query.append("ptf_login").append("=").append(username).append("&");
		query.append("ptf_endingurl").append("=").append(Constants.PTF_ENDINGURL).append("&");
		return query.toString();
	}
	
	public static String getTMMTargetServer(Object servers){
		String server = getJSONArrayIndData(servers, null);
		if (StringUtils.isEmpty(server))
			server = Constants.TMM_API_URL;
		return server;
	}
	
	private static String getNotNullValue(String value, String defaultV){
		if (StringUtils.isEmpty(value))
			return defaultV==null?"":defaultV;
		return value;
	}
	
	private static String getJSONArrayIndData(Object jsondata, Integer ind){
		String ret = null;
		if (jsondata==null) return ret;
		try{
			JSONArray array = (JSONArray) jsondata;
			if (ind!=null && array.length()>ind && ind>=0) ret = (String) array.get(ind);
			else if (array.length()>0) ret = (String) array.get(0);
		}catch (JSONException | ClassCastException  e){
			logger.error(e.getMessage(), e);
			try{
				ret = (String)jsondata;
			}catch (Exception e1){
				logger.error(e1.getMessage(), e1);
			}
		}
		return ret;
	}
}
