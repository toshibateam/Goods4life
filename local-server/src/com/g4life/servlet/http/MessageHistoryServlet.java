/* 
 *//***********************************************************************************
 * <summary>
 * Project name: server-application
 * Class name: MessageHistoryServlet.java
 * </summary>
 * <history>
 * VERSION	DATE	BY		CHANGE/COMMENT
 *--------------------------------------------------------------------------------------------------------------
 * V1.00	24-01-2017	(TSDV) 	Initialize source code
 * -------------------------------------------------------------------------------------------------------------
 * </history>
 * <Copyright>
 * (C) Copyright TOSHIBA Corporation 2016-2017. All rights reserved.                                             
 * </Copyright>                                                                                                                                  
 *******************************************************************************
 */
package com.g4life.servlet.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageHistoryServlet.
 */
@WebServlet("/GetAllMessageHistory")
public class MessageHistoryServlet extends BaseServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String communicationID = request.getParameter("communicationID");

		if (communicationID != null) {
//			MessageHistoryController controller = new MessageHistoryController();
//			JSONDocAbtract jsonDoc = controller.createJsonData(
//					communicationID, Ultility.getCurrentTime(),
//					controller.getAllMessage());
			String content = "doGet";
			
			responseRequest(response, content);

		} else {
			returnBadRequest(response, request);
		}
	}

}
