package luna.tmm.rosettastone.application.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import luna.tmm.rosettastone.utils.Constants;
import luna.tmm.rosettastone.utils.RetObject;
import luna.tmm.rosettastone.utils.TMMUtils;
import luna.tmm.rosettastone.utils.WebAPI;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("auth")
public class Authorization {
	
	protected final static Logger logger = LogManager.getLogger(Authorization.class);
	protected static ObjectMapper objectMapper = new ObjectMapper();
	
	private final WebAPI webApi = new WebAPI();
	
	/**
	 * @param tokenId
	 * @return
	 */
	@GET
	//@Path("/{token}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable auth(@PathParam("token") String tokenParam,
    		@CookieParam("RSID") String rsidcookie) {
		String targetPage = "/mock/mock";
		RSID rsid = getUserToken(rsidcookie);
		
		logger.info("------Login RSID-------" + rsid.toString());
		if (StringUtils.isEmpty(rsid.access_token)){
			logger.error("access_token is null...");
			return new Viewable(targetPage, getRetMap(RetObject.ERROR));
		}
		
		try {
			String authHeader = setTokenBearer(rsid.access_token);
			if (StringUtils.isEmpty(rsid.userId)){
				logger.log(Level.DEBUG, "----Get Token----" + Constants.AUTH_URL + rsid.access_token);
				RetObject token = webApi.call(Constants.AUTH_URL + rsid.access_token);
				logger.debug("---token---"+token.toString());
				
				Map tokenMap = objectMapper.readValue(token.getData(), Map.class);
				rsid.userId = (String) tokenMap.get("userId");
			}
			
			if ( !StringUtils.isEmpty(rsid.userId) ){
				logger.log(Level.DEBUG, "----Get User Info----" + Constants.SCHOLAR_URL + rsid.userId);
				RetObject scholarInfo = webApi.callMethod("GET", Constants.SCHOLAR_URL + rsid.userId,
						authHeader );
				logger.debug("---user info---"+scholarInfo.toString());
				
				Map userMap = objectMapper.readValue(scholarInfo.getData(), Map.class);
				logger.debug("----Get Organization Info----" + Constants.ORG_URL + userMap.get("organization"));
				RetObject orgInfo = webApi.callMethod("GET", Constants.ORG_URL + userMap.get("organization"),authHeader);
				logger.debug("---organization info---"+orgInfo.toString());
				
				String username = (String)userMap.get("userField6");
				Object rsaServers = userMap.get("rsaServers");
				//TODO TMM_API_URL should be read in organization
				return new Viewable(targetPage, getRetMap(RetObject.OK, "user", scholarInfo.getData(),
						"org", orgInfo.getData(), 
						"tmmurl", TMMUtils.getTMMTargetServer(rsaServers) 
								+ TMMUtils.getTMMParamQuery(username)) );
			} else {
				logger.error("---User Id is NULL---");
				return new Viewable(targetPage, getRetMap(RetObject.ERROR));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//return new RetObject(RetObject.RET_STATUS.ERROR, "error converting json data from sholar", "").toString();
			return new Viewable(targetPage, getRetMap(RetObject.ERROR));
		}
    }
	
	@POST
	@Path("cookie")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_HTML)
	public Response setCookies(@FormParam("rsid") String rsid) throws URISyntaxException{
		logger.debug(rsid);
		return Response.seeOther(new URI("auth/"))
				.cookie(new NewCookie("RSID", rsid)).build();
	}
	
	@GET
	@Path("/error")
	@Produces(MediaType.TEXT_HTML)
	public Viewable errorPage(@QueryParam("msg") String msg){
		return new Viewable("/pages/error", getRetMap("1", "msg", msg));
	}
	
	@PUT
	@Path("/test")
	@Consumes("application/x-www-form-urlencoded")
//	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String test(@FormParam("data") String data) {
        System.out.println("------test-------" + data);
        return data;
    }
	
	@GET
	@Path("/test2")
	@Produces(MediaType.TEXT_HTML)
	public Viewable test2(){
		return new Viewable("/mock/mock");
//		return new Viewable("/pages/error");
	}
	
	private String setTokenBearer(String token){
		return "Authorization: Bearer " + token;
	}
	
	private Map getRetMap(String status, Object... objs){
		Map map = new HashMap();
		map.put("status", status);
		for (int i=0; i<objs.length/2; i++){
			map.put(objs[i*2], objs[i*2+1]);
		}
		return map;
	}
	
	private RSID getUserToken(String rsid){
		RSID ret = null;
		try {
			ret = objectMapper.readValue(URLDecoder.decode(rsid, "UTF-8"), RSID.class);
		} catch (IOException | NullPointerException e) {
			logger.error(e.getMessage(), e);
		}
		return ret == null? new RSID():ret;
	}
}

class RSID{
	public String access_token;
	public String userId;
	public RSID(){}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "RSID [access_token=" + access_token + ", userId=" + userId
				+ "]";
	}
}
