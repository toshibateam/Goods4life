package com.g4life.service.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SellerInfo")
public class RegistrationSaleServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String content = req.getHeader("content");
		System.out.println("content: "+ content);
		InputStream input= req.getInputStream();
//		System.out.println("input: "+ input.read());
//		byte[] buffer = new byte[input.available()+2];
//		input.read(buffer);
		File targetFile = new File("D:/data/abc.jpg");
	    OutputStream outStream = new FileOutputStream(targetFile);
//	    outStream.write(buffer);
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = input.read(bytes)) != -1) {
			outStream.write(bytes, 0, read);
		}
		outStream.close();
	    outStream.close();
	}
}
