/* 
 *//***********************************************************************************
 * <summary>
 * Project name: server-application
 * Class name: BaseServlet.java
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

package com.g4life.service.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.g4life.common.ValueConstant;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class BaseServlet.
 */

public class BaseServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	//private LocalServerLogger logger = new LocalServerLogger();

	/**
	 * Instantiates a new base servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
	}

	/**
	 * Response request.
	 *
	 * @param response the response
	 * @param content the content
	 */
	public void responseRequest(HttpServletResponse response, String content) {

		try {
			response.setCharacterEncoding(ValueConstant.DEFAULT_ENCODING);
			PrintWriter writer = response.getWriter();
			writer.print(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return bad request.
	 *
	 * @param response the response
	 * @param request the request
	 */
	public void returnBadRequest(HttpServletResponse response, HttpServletRequest request) {


		String rqHeader = request.getHeader("origin");
		if (rqHeader != null) {

			try {
				String rqHeaderStr = URLEncoder.encode(rqHeader,StandardCharsets.UTF_8.displayName());
				
				// bad request is send by client
				response.setHeader("Access-Control-Allow-Origin",
						rqHeaderStr);
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	
	}

}
