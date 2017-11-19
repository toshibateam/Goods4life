/* 
 *//***********************************************************************************
	* <summary>
	* Project name: local-server
	* Class name: MessageNotifyServer.java
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
package com.g4life.service.websocket;

import java.io.IOException;
import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.g4life.common.ResponseCode;
import com.g4life.control.AccountController;
import com.g4life.dto.AccountInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * The Class MessageNotifyServer.
 */
@ServerEndpoint("/CreateAccount")
public class CreateUserAccountService {

	/** The queue. */
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

	/** The syn object. */
	private static Object synObject = new Object();

	/** The logger. */

	/**
	 * Open.
	 *
	 * @param session
	 *            the session
	 */
	@OnOpen
	public void open(Session session) {
		queue.add(session);
//		try {
//			//session.getBasicRemote().sendText(String.valueOf(ResponseCode.SUCCESS_CODE));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	/**
	 * On message.
	 *
	 * @param session
	 *            the session
	 * @param msg
	 *            the msg
	 */
	@OnMessage
	public void onMessage(Session session, String msg) {
		try {
			Gson gson = new Gson();
			AccountInfo account = gson.fromJson(msg, AccountInfo.class);
			//check account
			AccountController accountController = new AccountController();
			int result = accountController.createAccount(account);
			//create json object
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", result);
			if (result == ResponseCode.SUCCESS_CODE) {
				jsonObject.addProperty("jwt", accountController.getJWTOfAccount(account));
				jsonObject.addProperty("ConfirmCode", "123456");
			}
			session.getBasicRemote().sendText(jsonObject.toString());
		} catch (Exception e) {
			try {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("code", ResponseCode.ERROR_SERVER_CODE);
				session.getBasicRemote().sendText(jsonObject.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Error.
	 *
	 * @param session
	 *            the session
	 * @param t
	 *            the t
	 */
	@OnError
	public void error(Session session, Throwable t) {

		queue.remove(session);

	}

	/**
	 * Closed connection.
	 *
	 * @param session
	 *            the session
	 */
	@OnClose
	public void closedConnection(Session session) {

		queue.remove(session);

	}

	/**
	 * Send message.
	 *
	 * @param mess
	 *            the mess
	 */
	public static void sendMessage(String mess) {
		try {
			List<Session> closedSesstions = new ArrayList<>();
			synchronized (synObject) {

				for (Session session : queue) {

					if (!session.isOpen()) {

						closedSesstions.add(session);

					} else {
						session.getBasicRemote().sendText(mess);
					}

				}
			}
			closedSesstions.removeAll(closedSesstions);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
