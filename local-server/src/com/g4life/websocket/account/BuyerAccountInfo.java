package com.g4life.websocket.account;

import com.google.gson.annotations.SerializedName;

public class BuyerAccountInfo {
	@SerializedName("Address")
	public String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
