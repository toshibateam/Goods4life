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
package com.g4life.service.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.g4life.common.ValueConstant;
import com.g4life.control.ProductController;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageHistoryServlet.
 */
@WebServlet("/ProductInfo")
public class ProductInfoServlet extends BaseServlet {

	String charset = "UTF-8";
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String functionName = request.getParameter("functionName");
		System.out.println("functionName: "+ functionName);
		ProductController productController = new ProductController();
		String result = "";
		switch (functionName) {
		case ValueConstant.HOME_PRO_INFO:
			result = productController.getHomeProductInfo();
			responseRequest(response, result);
			break;
		default:
			returnBadRequest(response, request);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	/*
	 * PRIVATE VOID SAVEFILE() { STRING IMAGE1PATH = IMAGE_PATH_1; FILE
	 * IMAGE1FILE = NEW FILE(IMAGE1PATH); FILEINPUTSTREAM INPUTSTREAM1; STRING
	 * IMAGE2PATH = IMAGE_PATH_2; FILE IMAGE2FILE = NEW FILE(IMAGE2PATH);
	 * FILEINPUTSTREAM INPUTSTREAM2; TRY { INPUTSTREAM1 = NEW
	 * FILEINPUTSTREAM(IMAGE1FILE); INPUTSTREAM2 = NEW
	 * FILEINPUTSTREAM(IMAGE2FILE); FILE F = NEW
	 * FILE("/C:/USERS/HUY DUAN/DESKTOP/_DSC0237.JPG"); SEQUENCEINPUTSTREAM
	 * TOTALINPUT = NEW SEQUENCEINPUTSTREAM(INPUTSTREAM2, INPUTSTREAM1);
	 * 
	 * OUTPUTSTREAM OS = NEW FILEOUTPUTSTREAM(F); BYTE[] BUF = NEW
	 * BYTE[(INT)(IMAGE1FILE.LENGTH()+ IMAGE2FILE.LENGTH())];
	 * SYSTEM.OUT.PRINTLN("LEN1: "+ IMAGE1FILE.LENGTH() + ":"+
	 * INPUTSTREAM1.TOSTRING()); SYSTEM.OUT.PRINTLN("LEN2: "+
	 * IMAGE2FILE.LENGTH()+ ":"+ INPUTSTREAM2.TOSTRING());
	 * SYSTEM.OUT.PRINTLN("LEN: "+ BUF.LENGTH); INT LEN = -1; INT TEMP =
	 * TOTALINPUT.READ(BUF, (INT)IMAGE1FILE.LENGTH(), (INT)IMAGE2FILE.LENGTH());
	 * SYSTEM.OUT.PRINTLN("TEMP: "+ TEMP); IF((LEN = TOTALINPUT.READ(BUF)) !=
	 * -1) { SYSTEM.OUT.PRINTLN("WRITE IMAGE FILE"); OS.WRITE(BUF, 0,
	 * (INT)IMAGE1FILE.LENGTH()); } // WHILE ((LEN = INPUTSTREAM2.READ(BUF)) !=
	 * -1) { // OS.WRITE(BUF, 0, LEN); // } INPUTSTREAM1.CLOSE();
	 * INPUTSTREAM2.CLOSE(); OS.CLOSE(); } CATCH (IOEXCEPTION E) {
	 * E.PRINTSTACKTRACE(); SYSTEM.OUT.PRINTLN("ERROR"); }
	 * 
	 * }
	 */
	/*
	 * public void getResponse(String[] args) { 
	 * ProductController productController = new ProductController();
		List<ProductInfo> lisProductInfos = new ArrayList<ProductInfo>();
		OutputStream outStream = null;
		InputStream inputStream = null;
		try {

			lisProductInfos = productController.getAllNewestProducts();
			// obtains ServletContext
			ServletContext context = getServletContext();
			inputStream = productController.getImageByte();
			/*
			 * // gets MIME type of the file String mimeType = ""; if (mimeType
			 * == null) { // set to binary type if MIME mapping not found
			 * mimeType = "jpg"; }
			 

			// modifies response
			response.setContentType("jpg");

			JsonObject data = new JsonObject();
			long size = 0;
			for (int i = 0; i < lisProductInfos.size(); i++) {
				data.addProperty("imageName_" + (i+1), lisProductInfos.get(i)
						.getProductName());
				data.addProperty("length_" + (i+1),
						String.valueOf(lisProductInfos.get(i).getSizeImage()));
				size = size + lisProductInfos.get(i).getSizeImage();
			}
			response.setHeader("data", data.toString());
			response.setDateHeader("responseTime", new Date().getTime());
			response.setIntHeader("code", 200);
			outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (outStream != null && inputStream != null) {
				try {
					outStream.close();
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}} }*/

}
