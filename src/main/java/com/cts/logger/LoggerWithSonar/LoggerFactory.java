package com.cts.logger.LoggerWithSonar;

import org.slf4j.Logger;

public class LoggerFactory {
	private LoggerFactory(){}
	public static Logger getLogger(String className){
		
			return  LoggerFactory.getLogger(className+".class");
	}

}
