package com.ls.logging;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;

import com.ls.logging.AppRuntime;
import com.ls.logging.LSLogger;

public class LSLoggerFactory implements LoggerFactory{


	 /**
   * The default constructor merely calls the three-argument constructor with
   * null values.
   */
  public LSLoggerFactory() {
	   LSLogger.setFactory(this);
  }
	
	/**
   * Create a new instance of <code>CtfCategory</code> using the information
   * contained in this instance.
   */
  public Logger makeNewLoggerInstance(String name) {
      Logger result = new LSLogger(name);
      return result;
  }

  static
	{
      try
		{
        
   	   Properties props = AppRuntime.getLoggerConfigurationFile();
          PropertyConfigurator.configure(props);
      }
		catch (Throwable e)
		{
         e.printStackTrace();
      }
  }

}
