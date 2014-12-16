package luna.tmm.rosettastone.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

public class WebAPI {
	
	protected static final Logger logger = LogManager.getLogger(WebAPI.class);
	
	private Boolean isContext = true;
	
	protected ClientConnectionManager connectionManager;
	protected SchemeRegistry schemeRegistry;
	protected HttpParams httpParams;
	private DefaultHttpClient httpClient;
	
	 private static final Map<String,String> HttpMethodMap = new HashMap<String,String>(){{
		 put("GET", "org.apache.http.client.methods.HttpGet");
		 put("POST", "org.apache.http.client.methods.HttpPost");
		 put("DELETE", "org.apache.http.client.methods.HttpDelete");
		 put("PUT", "org.apache.http.client.methods.HttpPut");
	 }};
	 
	 public static enum HttpMethod{
		 GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");
		 
		 String method;
		 
		 private HttpMethod(String method){
			 this.method = method;
		 }
		 
		 public String getMethod(){
			 return method;
		 }
		 
	 }
	 
	 private static final String HTTP_GET = "GET";
	 private static final String HTTP_POST = "POST";
	 private static final String HTTP_PUT = "PUT";
	 private static final String HTTP_DELETE = "DELETE";
	 public final static int MAX_TOTAL_CONNECTIONS = 800; 
	 public final static int WAIT_TIMEOUT = 5000;  
	 public final static int MAX_ROUTE_CONNECTIONS = 400;  
	 public final static int CONNECT_TIMEOUT = 5000;  
	 public final static int READ_TIMEOUT = 10000; 
	 
	@SuppressWarnings("deprecation")
	public WebAPI(){
		initClient();
	}
	
	public WebAPI(Boolean needReturnContext){
		this.isContext = needReturnContext;
		initClient();
	}
	
	private void initClient(){
		httpClient = new DefaultHttpClient();
		connectionManager = httpClient.getConnectionManager();
		httpParams = httpClient.getParams();
		
		ConnManagerParams.setTimeout(httpParams, WAIT_TIMEOUT);
		ConnManagerParams.setMaxTotalConnections(httpParams, MAX_TOTAL_CONNECTIONS); 
		
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(MAX_ROUTE_CONNECTIONS);  
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,connPerRoute); 
        
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);  
        HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);
		
		httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(httpParams,
				connectionManager.getSchemeRegistry()), httpParams);
	}
	
	/**
	 * @param url
	 * @return HttpResponse
	 */
	public RetObject call(String url){
		return callMethod("GET", url);
	}
	
	/**
	 * @param method
	 * @param url
	 * @return
	 */
	public RetObject callMethod(String method, String url){
		return callMethod(method, url, "" , null);
	}
	
	/**
	 * @param method
	 * @param url
	 * @param headers
	 * @return
	 */
	public RetObject callMethod(String method, String url, String headers){
		return callMethod(method, url, getHeaders(headers), null);
	}
	
	/**
	 * @param method
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	public RetObject callMethod(String method, String url, String headers, String params){
		return callMethod(method, url, getHeaders(headers), gerParams(params));
	}
	
	/**
	 * @param method
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	public RetObject callMethod(String method, String url, Map<String,String> headers, List<NameValuePair> params) {
		HttpRequestBase request = null;
		try {
			if (HTTP_GET.equalsIgnoreCase(method)){
				request = httpGet(url,headers, params);
			}else if (HTTP_POST.equalsIgnoreCase(method)){
				request = httpPost(url,headers, params);
			}else if (HTTP_PUT.equalsIgnoreCase(method)){
				request = httpPut(url,headers, params);
			}else if (HTTP_DELETE.equalsIgnoreCase(method)){
				request = httpDelete(url,headers, params);
			}
			HttpResponse res = httpClient.execute(request);
			return new RetObject(RetObject.RET_STATUS.OK, 
					res.getStatusLine().getReasonPhrase(),
						res.getStatusLine().getStatusCode(),
							isContext?EntityUtils.toString(res.getEntity()):"" );
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return new RetObject(RetObject.RET_STATUS.ERROR, e.getMessage(), "");
		}finally{
			if (request!=null)
				request.releaseConnection();
		}
	}
	
	/**
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	private HttpGet httpGet(String url, Map<String,String> headers, List<NameValuePair> params){
		//TODO parameters
		HttpGet ret = new HttpGet(url);
		SetHeaders(ret, headers);
		return ret;
	}
	
	/**
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private HttpPost httpPost(String url, Map<String,String> headers, List<NameValuePair> params) throws UnsupportedEncodingException{
		HttpPost ret = new HttpPost(url);
		SetHeaders(ret, headers);
		setParams(ret, params);
		return ret;
	}
	
	/**
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private HttpPut httpPut(String url, Map<String,String> headers, List<NameValuePair> params) throws UnsupportedEncodingException{
		HttpPut ret = new HttpPut(url);
		SetHeaders(ret, headers);
		setParams(ret, params);
		return ret;
	}
	
	/**
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	private HttpDelete httpDelete(String url, Map<String,String> headers, List<NameValuePair> params){
		HttpDelete ret = new HttpDelete(url);
		SetHeaders(ret, headers);
		return ret;
	}
	
	/**
	 * @param request
	 * @param headers
	 */
	private void SetHeaders(HttpRequest request, Map<String,String> headers){
		if (headers != null)
			for (Map.Entry<String, String> item : headers.entrySet())
				request.setHeader(item.getKey() , item.getValue());
	}
	
	/**
	 * @param request
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	private void setParams(HttpEntityEnclosingRequestBase request, List<NameValuePair> params) throws UnsupportedEncodingException{
		if (params != null)
			request.setEntity(new UrlEncodedFormEntity(params));
	}
	
	/**
	 * @param headers
	 * @return
	 */
	private Map<String, String> getHeaders(String headers){
		if (StringUtils.isEmpty(headers))
			return null;
		Map<String,String> ret = new HashMap<String,String>();
		String[] headersArray = headers.split(",");
		for (String item : headersArray){
			String[] header = item.split(":");
			if (header.length > 1)
				ret.put(header[0], header[1]);
		}
		return ret;
	}
	
	/**
	 * @param params
	 * @return
	 */
	private List<NameValuePair> gerParams(String params){
		if (StringUtils.isEmpty(params))
			return null;
		List<NameValuePair> ret = new ArrayList<NameValuePair>();
		String[] headersArray = params.split("&");
		for (String item : headersArray){
			String[] header = item.split("=");
			ret.add(new BasicNameValuePair(header[0], header.length > 1?header[1]:""));
		}
		return ret;
	}
	
	/**
	 * @param method
	 * @return
	 */
	private Object getRequest(String method){
		if (HttpMethodMap.containsKey(method))
			try {
				return Class.forName( HttpMethodMap.get(method) ).newInstance();
			} catch (InstantiationException |  IllegalAccessException
					| ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			} 
		return null;
	}
	
	public Boolean getIsContext() {
		return isContext;
	}

	public void setIsContext(Boolean isContext) {
		this.isContext = isContext;
	}
}
