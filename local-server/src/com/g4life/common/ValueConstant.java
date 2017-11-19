/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: ValueConstant.java
 * </summary>
 * <history>
 * VERSION	DATE	BY		CHANGE/COMMENT
 *--------------------------------------------------------------------------------------------------------------
 * V1.00	22-09-2016	(TSDV) 	Initialize source code
 * V1.01	24-01-2017  (TSDV)  Add encoding define
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
 * The Interface ValueConstant.
 */
public interface ValueConstant {
	

	public static final String DATA_JSON = "data";
	public static final String IMAGE_PATH = "./data/image/";
	/** The Constant DEFAULT_ENCODING. */
	// Start V1.01
    public static final String DEFAULT_ENCODING = "UTF-8";
    //Type acocunt
    public static final int TYPE_ADMIN = 1;
    public static final int TYPE_SELLER = 2;
    public static final int TYPE_BUYER = 3;
    //Type sign in
    public static final int SIGN_IN_BY_FACEBOOK = 5;
    public static final int SIGN_IN_BY_TOKEN = 6;
    public static final int SING_IN_BY_MAIL = 7;
    public static final int SING_IN_BY_PHONE = 8;
    //Type function request
    public static final String HOME_PRO_INFO = "GetHomeInfo";
    
    
}
