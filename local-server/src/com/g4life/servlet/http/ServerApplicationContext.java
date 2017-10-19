/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: ApplicationContext.java
 * </summary>
 * <history>
 * VERSION	DATE	BY		CHANGE/COMMENT
 *--------------------------------------------------------------------------------------------------------------
 * V1.00	22-09-2016	(TSDV) 	Initialize source code
 * -------------------------------------------------------------------------------------------------------------
 * </history>
 * <Copyright>
 * (C) Copyright TOSHIBA Corporation 2016-2017. All rights reserved.                                             
 * </Copyright>                                                                                                                                  
 *******************************************************************************
 */
package com.g4life.servlet.http;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.g4life.common.ValueConstant;
import com.toshiba.bacnet.util.LogManager;


// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationContext.
 */
public class ServerApplicationContext implements ServletContextListener {


	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
//		String mess = logger.prepareMessage(ValueConstant.LOG_INFO,
//				"Application is shutdown");
//		
//		logger.printMessageLog(mess);

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("start service");
		
	}

}
