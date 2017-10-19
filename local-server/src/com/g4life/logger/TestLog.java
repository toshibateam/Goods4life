/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: TestLog.java
 * </summary>
 * <history>
 * VERSION	DATE	BY		CHANGE/COMMENT
 *--------------------------------------------------------------------------------------------------------------
 * V1.00	24-03-2017	(TSDV) 	Initialize source code
 * -------------------------------------------------------------------------------------------------------------
 * </history>
 * <Copyright>
 * (C) Copyright TOSHIBA Corporation 2016-2017. All rights reserved.                                             
 * </Copyright>                                                                                                                                  
 *******************************************************************************
 */
package com.g4life.logger;

// TODO: Auto-generated Javadoc
/**
 * The Class TestLog. ONLY FOR TEST
 */
public class TestLog {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		LogWriterManager logwriter = LogWriterManager.getPackageLogWriter("Local-server");
		while(true) {
			
			Exception e = new Exception("Exception message");
			logwriter.addLog(e);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
	}

}
