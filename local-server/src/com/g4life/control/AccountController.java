package com.g4life.control;


import com.g4life.common.ResponseCode;
import com.g4life.common.ValueConstant;
import com.g4life.common.json.JsonWebTokenAccount;
import com.g4life.dao.AccountInfoAccess;
import com.g4life.dto.AccountInfo;
import com.g4life.service.confirmaccount.SendToEmail;

public class AccountController {

	public int createAccount(AccountInfo accountInfo) {
		AccountInfoAccess access = new AccountInfoAccess();
		accountInfo.setAccountID((int) System.currentTimeMillis());
		if (accountInfo.getMail() != null && !accountInfo.getMail().isEmpty()) {
			// sign up by mail
			// check mail exist or not
			String mail = accountInfo.getMail();
			if (access.getByMail(mail) == null) {
				access.insertOrUpdate(accountInfo);
				//send code to email
				String code = "123456";
				SendToEmail sendToEmail = new SendToEmail();
				sendToEmail.sendCodeToEmail(mail, code);
				return ResponseCode.SUCCESS_CODE;
			}
		} else if (accountInfo.getPhoneNumber() != null
				&& !accountInfo.getPhoneNumber().isEmpty()) {
			// sign up by phone number
			// check phone exist or not
			String phone = accountInfo.getPhoneNumber();
			if (access.getByPhoneNumber(phone) == null) {
				access.insertOrUpdate(accountInfo);
				//TODO send code to phone number
				return ResponseCode.SUCCESS_CODE;
			}
			
		}
		return 0;
	}

	public int signInAccount(AccountInfo accountInfo, int typeSignIn) {
		AccountInfoAccess access = new AccountInfoAccess();
		if (typeSignIn == ValueConstant.SIGN_IN_BY_FACEBOOK) {
			//check user exist or not
			if (accountInfo.getUserName() != null) {
				String userName = accountInfo.getUserName();
				if (access.getByUserName(userName) == null) {
					accountInfo.setAccountID((int) System.currentTimeMillis());
					//update account to database
					access.insertOrUpdate(accountInfo);
				}
			}
		}else if (typeSignIn == ValueConstant.SIGN_IN_BY_TOKEN) {
			return ResponseCode.SUCCESS_CODE;
		}else if (typeSignIn == ValueConstant.SING_IN_BY_MAIL) {
			String mail = accountInfo.getMail();
			if (access.getByMail(mail) != null) {
				AccountInfo account = access.getByMail(mail);
				// check password
				if (account.getPassWord().compareTo(accountInfo.getPassWord()) == 0) {
					return ResponseCode.SUCCESS_CODE;
				}
			}
		}else {
			String phone = accountInfo.getPhoneNumber();
			if (access.getByPhoneNumber(phone) != null) {
				AccountInfo account = access.getByPhoneNumber(phone);
				//check password
				if (account.getPassWord().compareTo(accountInfo.getPassWord()) == 0) {
					return ResponseCode.SUCCESS_CODE;
				}
			}
		}
		return ResponseCode.ERROR_SIGN_IN;
	}

	public String getJWTOfAccount(AccountInfo accountInfo) {
		JsonWebTokenAccount jsonAccount = new JsonWebTokenAccount();
		AccountInfoAccess accountInfoAccess = new AccountInfoAccess();
		String result = "";
		if (accountInfo.getMail() != null) {
			AccountInfo account = accountInfoAccess.getByMail(accountInfo.getMail());
			result = jsonAccount.createJWT(account,
					ValueConstant.SING_IN_BY_MAIL);
		} else if (accountInfo.getPhoneNumber() != null) {
			AccountInfo account = accountInfoAccess.getByPhoneNumber(accountInfo.getPhoneNumber());
			result = jsonAccount.createJWT(account,
					ValueConstant.SING_IN_BY_PHONE);
		}
		return result;
	}

	public AccountInfo getAccountFromJWT(String jwt) {
		JsonWebTokenAccount jsTokenAccount = new JsonWebTokenAccount();
		AccountInfo accountInfo = null;
		accountInfo = jsTokenAccount.getAccountFromJWT(jwt);
		return accountInfo;
	}

}
