/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: JSON.java
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
package com.g4life.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class JSON.
 */
public class JSON {
	/** The Constant parser. */
	public static final Gson parser = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();
}
