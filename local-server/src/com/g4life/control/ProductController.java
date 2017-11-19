package com.g4life.control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

import com.g4life.common.json.HomeInfoJsonDoc;
import com.g4life.dao.ProductInfoAccess;
import com.g4life.dto.ProductInfo;



public class ProductController{
//	GetNewestDataExecutor getNewestDataExecutor;
//	public ProductController() {
//		getNewestDataExecutor = new GetNewestDataExecutor();
//	}
//	
//	public List<ProductInfo> getAllNewestProducts() {
//		List<ProductInfo> productInfos = null;
//		productInfos = getNewestDataExecutor.readImage();
//		return productInfos;
//	}
//	public InputStream getImageByte() {
//		List<InputStream> lisInputStreams = getNewestDataExecutor.getListImageData();
//		System.out.println("list input size: "+ lisInputStreams.size());
//		Vector<InputStream> vector = new Vector<InputStream>();
//		if (lisInputStreams != null) {
//			for (int i = 0; i < lisInputStreams.size(); i++) {
//				vector.add(lisInputStreams.get(i));
//			}
//			Enumeration<InputStream> enu = vector.elements();
//			SequenceInputStream result = new SequenceInputStream(enu);
//			return result;
//		}
//		return null;
//	}
	public String getHomeProductInfo() {
		ProductInfoAccess productInfoAccess = new ProductInfoAccess();
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
//		productInfos = productInfoAccess.getAllProduct();
		//TODO create test data
		Date startDate = new Date();
		Date endDate = new Date();
		ProductInfo data1 = new ProductInfo(1,2,4,"hoa qua","15000VNĐ","Kg",25,startDate, endDate, 0, "true",50,"na_thai","", "" );
		ProductInfo data2 = new ProductInfo(1,2,5,"hoa qua","15000VNĐ","Kg",25,startDate, endDate, 0, "true",50,"du_du","", "" );
		ProductInfo data3 = new ProductInfo(1,2,6,"hoa qua","15000VNĐ","Kg",25,startDate, endDate, 0, "true",50,"mit_thai","", "" );
		ProductInfo data4 = new ProductInfo(1,2,7,"hoa qua","15000VNĐ","Kg",25,startDate, endDate, 0, "true",50,"buoi_dien","", "" );
		productInfos.add(data4);
		productInfos.add(data3);
		productInfos.add(data2);
		productInfos.add(data1);
		HomeInfoJsonDoc homeInfoJsonDoc = new HomeInfoJsonDoc(productInfos);
		String response = homeInfoJsonDoc.getJSONDoc();
		return response;
	}
}
