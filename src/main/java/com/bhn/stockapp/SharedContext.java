package com.bhn.stockapp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedContext {
	
	private static Logger logger = LoggerFactory.getLogger(SharedContext.class);
	
	public static final String CURRENT_TIME_IN_MILLIS = "current_time_in_millis";
	private Map<String,Object> sharedMap = new HashMap<String,Object>();
	private static SharedContext instance;
	
	private SharedContext(){
		logger.info("### Creating shared context...");
		sharedMap.put(CURRENT_TIME_IN_MILLIS, new Long(System.currentTimeMillis()));
	}
	
	public static SharedContext getInstance() {
		if ( instance == null ) {
			synchronized(SharedContext.class) {
				instance = new SharedContext();
			}
		}
		return instance;
	}
	
	public synchronized void refresh() {
		logger.info("Refreshing key => {}", CURRENT_TIME_IN_MILLIS);
		sharedMap.remove(CURRENT_TIME_IN_MILLIS);
		sharedMap.put(CURRENT_TIME_IN_MILLIS, new Long(System.currentTimeMillis()));
	}
	
	public Object getValue(String key) {
		//logger.info("Search for key => {}" , key);
		if ( sharedMap.containsKey(key)) {
			return sharedMap.get(key);
		}
		return null;
	}

}
