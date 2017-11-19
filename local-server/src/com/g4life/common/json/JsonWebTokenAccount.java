package com.g4life.common.json;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.g4life.common.ValueConstant;
import com.g4life.dto.AccountInfo;

public class JsonWebTokenAccount {
	public String createJWT(AccountInfo account, int checkItem) {
		String userNameJson;
		String userName;
		String address = "";
		int id = account.getAccountID();
		int accountType = account.getAccountType();
		if (checkItem == ValueConstant.SING_IN_BY_PHONE) {
			userNameJson = "PhoneNumber";
			userName = account.getPhoneNumber();
		} else if (checkItem == ValueConstant.SING_IN_BY_MAIL) {
			userNameJson = "Mail";
			userName = account.getMail();
		} else {
			return null;
		}
		if (account.getAddress() != null) {
			address = account.getAddress();
		}
		try {
			String jwt = Jwts
					.builder()
					.setSubject("account")
					.setExpiration(new Date(1300819380))
					.claim(userNameJson, userName)
					.claim("AccountID", id)
					.claim("UserName", account.getUserName())
					.claim("Address", address)
					.claim("AccountType", accountType)
					.signWith(SignatureAlgorithm.HS256,
							"secret".getBytes("UTF-8")).compact();
			return jwt;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AccountInfo getAccountFromJWT(String jwt) {

		try {
			AccountInfo account = new AccountInfo();
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey("secret".getBytes("UTF-8"))
					.parseClaimsJws(jwt);
			
			String mail = (String) claims.getBody().get("Mail");
			String phoneNumber = (String) claims.getBody().get("PhoneNumber");
			String userName = (String) claims.getBody().get("UserName");
			String address = (String) claims.getBody().get("Address");
			
			account.setUserName(userName);
			account.setMail(mail);
			account.setPhoneNumber(phoneNumber);
			account.setAddress(address);
			
			return account;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
