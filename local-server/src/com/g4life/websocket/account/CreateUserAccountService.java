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
package com.g4life.websocket.account;

import java.io.IOException;
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

import com.g4life.common.JSonDataParser;
import com.g4life.common.ValueConstant;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * The Class MessageNotifyServer.
 */
@ServerEndpoint("/CreateAcount")
public class CreateUserAccountService {

	/** The queue. */
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

	/** The syn object. */
	private static Object synObject = new Object();
	
	/** The logger. */

	/**
	 * Open.
	 *
	 * @param session the session
	 */
	@OnOpen
	public void open(Session session) {
		queue.add(session);
		try {
			session.getBasicRemote().sendText("connect success");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * On message.
	 *
	 * @param session the session
	 * @param msg the msg
	 */
	@OnMessage
	 public void onMessage(Session session, String msg) {
	  try {
		  session.getBasicRemote().sendText(msg);
		  JsonParser jsonParser = new JsonParser();
		  JsonObject jsonObject = (JsonObject) jsonParser.parse(msg);
		  String jsonArray = jsonObject.get(ValueConstant.DATA_JSON).getAsJsonArray().toString();
		  AbstractAccount valueDatas =  (AbstractAccount) JSonDataParser.getObject(jsonArray,AbstractAccount.class);		  
		  session.getBasicRemote().sendText(valueDatas.getUserName());
		  session.getBasicRemote().sendText(valueDatas.getPassWord());

	  } catch (Exception e) {
		  e.printStackTrace();
	  }


	 }

	
	/**
	 * Error.
	 *
	 * @param session the session
	 * @param t the t
	 */
	@OnError
	public void error(Session session, Throwable t) {

		queue.remove(session);

	}

	/**
	 * Closed connection.
	 *
	 * @param session the session
	 */
	@OnClose
	public void closedConnection(Session session) {

		queue.remove(session);


	}

	/**
	 * Send message.
	 *
	 * @param mess the mess
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
	public static void main(String[] args) {
	}
}
