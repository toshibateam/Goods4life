/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: JSONDocAbtract.java
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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// TODO: Auto-generated Javadoc
/**
 * The Class JSONDocAbtract.
 */
public abstract class JSONDocAbtract {
	
	/** The communication id. */
	@SerializedName("communicationID")
	@Expose
	protected String communicationID;
	
	/** The response time. */
	@SerializedName("responseTime")
	@Expose (deserialize = false)
	protected String responseTime;
	
	/** The response time. */
	@SerializedName("code")
	@Expose (deserialize = false)
	protected String code = "200";
	
	/**
	 * Instantiates a new JSON doc abtract.
	 *
	 * @param communicationID the communication id
	 * @param responsTime the respons time
	 */
	public JSONDocAbtract(String communicationID, String responsTime) {
		super();
		this.communicationID = communicationID;
		this.responseTime = responsTime;
		
	}

	/**
	 * Gets the JSON doc.
	 *
	 * @return the JSON doc
	 */
	public abstract String getJSONDoc();
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}


}
