package com.ls.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.ls.logging.LSLogger;
import com.ls.logging.LSLoggerFactory;

public class LSLogger extends Logger {

	/**
     * A reference to the factory to create <code>LSLogger</code> instances.
     */
    private static LoggerFactory factory = new LSLoggerFactory();

	/**
	 * @param String loggerName
	 */
	public LSLogger(String loggerName) {
		super(loggerName);
		
	}
	
	 /**
     * Return a <code>LSLogger</code> instance with the provided name. If
     * such an instance exists, return it. Otherwise, create a new one.
     */
    public static LSLogger getLSLogger(String name) {
        return (LSLogger) Logger.getLogger(name, factory);
    }
    
    /**
     * Set the factory instance for creation of <code>LSLogger</code>
     * instances. in the <code>getInstance</code> method.
     * 
     * @param factory an <code>LSLogger</code> factory
     */
    public static void setFactory(LoggerFactory factory) {
    	LSLogger.factory = factory;
    }
	
	/**
     * Return a <code>LSLogger</code> instance with the provided class name.
     * If such an instance exists, return it. Otherwise, create a new one.
     */
    public static LSLogger getLSLogger(Class<?> clz) {
        return getLSLogger(clz.getName());
    }
	

}
