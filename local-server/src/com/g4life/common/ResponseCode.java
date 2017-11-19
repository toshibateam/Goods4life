/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: ResponseCode.java
 * </summary>
 * <history>
 * VERSION	DATE	BY		CHANGE/COMMENT
 *--------------------------------------------------------------------------------------------------------------
 * V1.00	08-03-2017	(TSDV) 	Initialize source code
 * -------------------------------------------------------------------------------------------------------------
 * </history>
 * <Copyright>
 * (C) Copyright TOSHIBA Corporation 2016-2017. All rights reserved.                                             
 * </Copyright>                                                                                                                                  
 *******************************************************************************
 */
package com.g4life.common;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseCode.
 */
public class ResponseCode {

	/** The Constant SUCCESS_CODE. */
	public static final int SUCCESS_CODE = 200;
	
	/** The Constant ERROR_CODE. */
	public static final int ERROR_SERVER_CODE = -100;

	// error code return to client
	public static final int ERROR_USER = -1;
	public static final int ERROR_MAIL = -2;
	public static final int ERROR_PHONE_NUMBER = -3;
	public static final int ERROR_SIGN_IN = -4;


}
