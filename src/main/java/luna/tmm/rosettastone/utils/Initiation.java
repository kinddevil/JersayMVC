package luna.tmm.rosettastone.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Initiation {
	
	private static final Logger logger = LogManager.getLogger(Initiation.class);
	private static ResourceBundle bundle;;
	
	public Initiation(){
		bundle = ResourceBundle.getBundle("resources/init");
	}
	
	@PostConstruct
	public void initParameters(){
		logger.debug("--------Initial Parameters----------");
		Constants.PTF_CMD = getProperty("PTF_CMD", Constants.PTF_CMD);
		Constants.PTF_PARTNER = getProperty("PTF_PARTNER",Constants.PTF_PARTNER);
		Constants.PTF_KEY = getProperty("PTF_KEY",Constants.PTF_KEY);
		Constants.PTF_METHOD = getProperty("PTF_METHOD", Constants.PTF_METHOD);
		Constants.PTF_ENDINGURL = getProperty("PTF_ENDINGURL", Constants.PTF_ENDINGURL);
		Constants.AUTH_URL = getProperty("AUTH_URL", Constants.AUTH_URL);
		Constants.SCHOLAR_URL = getProperty("SCHOLAR_URL", Constants.SCHOLAR_URL);
		Constants.ORG_URL = getProperty("ORG_URL", Constants.ORG_URL);
		Constants.TMM_API_URL = getProperty("TMM_API_URL", Constants.TMM_API_URL);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private String getProperty(String key, String defaultValue){
		try{
			return bundle.getString(key);
		}catch (MissingResourceException e){
			logger.error(e.getMessage());
			return defaultValue;
		}
	}
	
}
