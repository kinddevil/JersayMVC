package luna.tmm.rosettastone.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RetObject {
	
	protected static final Logger logger = LogManager.getLogger(RetObject.class);
	
	protected static ObjectMapper objectMapper = new ObjectMapper();
	
	public static final String OK = "1";
	public static final String ERROR = "0";
	
	public static enum RET_STATUS{
		OK(1), ERROR(0);
		
		private Integer status;
		
		private RET_STATUS(Integer status){
			this.status = status;
		}
		
		private Integer getStatus(){
			return this.status;
		}
	}
	
	private String status;
	private String msg;
	private String msgCode;
	private String data;
	
	public RetObject(RET_STATUS status, String msg, String data){
		fillObject(null, msg, null, data);
		this.status = status.getStatus().toString();
	}
	
	public RetObject(RET_STATUS status, String msg, String msgCode, String data){
		fillObject(null, msg, msgCode, data);
		this.status = status.getStatus().toString();
	}
	
	public RetObject(RET_STATUS status, String msg, Integer msgCode, String data){
		fillObject(null, msg, null, data);
		this.status = status.getStatus().toString();
		this.msgCode = msgCode.toString();
	}
	
	public RetObject(String status, String msg, String data){
		fillObject(status, msg, null, data);
	}
	
	public RetObject(String status, String msg, String msgCode, String data){
		fillObject(status, msg, msgCode, data);
	}
	

	private void fillObject(String status, String msg, String msgCode, String data) {
		this.status = status;
		this.msg = msg;
		this.msgCode = msgCode;
		this.data = data;
	}

	@Override
	public String toString() {
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return "{\"status\":\"" + status + "\", \"msg\":\"" + msg + "\", \"msgCode\":\""
				+ msgCode + "\", \"data\":\"" + data + "\"}"; 
		}
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
