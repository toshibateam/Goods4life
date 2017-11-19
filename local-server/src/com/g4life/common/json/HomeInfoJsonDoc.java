package com.g4life.common.json;

import java.util.List;

import com.g4life.dto.ProductInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeInfoJsonDoc extends JSONDocAbtract{
	@Expose
	@SerializedName("data")
	List<ProductInfo> productInfos;
	
	public HomeInfoJsonDoc(List<ProductInfo> productInfos) {
		super();
		this.productInfos = productInfos;
	}
	@Override
	public String getJSONDoc() {
		return JSON.parser.toJson(this);
	}
}
