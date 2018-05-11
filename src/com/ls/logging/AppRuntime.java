package com.ls.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AppRuntime {

    
	    public static Properties getLoggerConfigurationFile() {   
	    	try {
	    		Properties props = new Properties();
	    		ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    		InputStream resourceStream = loader.getResourceAsStream("Log4j.properties");
				props.load(resourceStream);
				return props;
			}catch (IOException io) {
				io.printStackTrace();
			} return null;
          

	    }
}
