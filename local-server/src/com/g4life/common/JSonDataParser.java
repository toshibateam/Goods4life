/* 
 *//***********************************************************************************
 * <summary>
 * Project name: RasPi
 * Class name: JSonDataParser.java
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

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Class JSonDataParser.
 */
public class JSonDataParser{
	public static final String	DATE_TIME_FORMATER				= "yyyy/MM/dd HH:mm:ss";

	/** The gson. */
	private static Gson gson = new GsonBuilder().setDateFormat(DATE_TIME_FORMATER).create();

	/**
	 * Gets the object.
	 *
	 * @param json
	 *            the json
	 * @param returnClass
	 *            the return class
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	public static Object getObject(String json, Class<?> returnClass) throws Exception {
		return gson.fromJson(json, returnClass);
	}

	/**
	 * Gets the list object.
	 *
	 * @param jsonArray
	 *            the json array
	 * @param type
	 *            the type
	 * @return the list object
	 * @throws Exception
	 *             the exception
	 */
	public static Object getListObject(String jsonArray, Type type) throws Exception {
		return gson.fromJson(jsonArray, type);
	}

	/**
	 * Gets the json.
	 *
	 * @param obj
	 *            the obj
	 * @return the json
	 * @throws Exception
	 *             the exception
	 */
	public static String getJSON(Object obj) throws Exception {
		return gson.toJson(obj);
	}

	/**
	 * Gets the JSON array.
	 *
	 * @param listData
	 *            the list data
	 * @return the JSON array
	 * @throws Exception
	 *             the exception
	 */
	public static String getJSONArray(List<?> listData) throws Exception {
		return gson.toJson(listData);
	}

}
