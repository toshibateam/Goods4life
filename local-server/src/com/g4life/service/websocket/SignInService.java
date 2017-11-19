package com.g4life.service.websocket;

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

import org.json.JSONObject;

import com.g4life.common.ResponseCode;
import com.g4life.common.ValueConstant;
import com.g4life.control.AccountController;
import com.g4life.dao.AccountInfoAccess;
import com.g4life.dto.AccountInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@ServerEndpoint("/SignIn")
public class SignInService {

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
			AccountController accountController = new AccountController();
			//check sign in by jwt or not
			JSONObject jsonObject = new JSONObject(msg);
			AccountInfo account = new AccountInfo();
			int typeSignIn = jsonObject.getInt("typeSignIn");
			if (typeSignIn == ValueConstant.SIGN_IN_BY_TOKEN) {
				// Do nothing
			}else {
				Gson gson = new Gson();
				String data = jsonObject.get("data").toString();
				account = gson.fromJson(data, AccountInfo.class);
			}
			
			int result = accountController.signInAccount(account, typeSignIn);
			JsonObject jsoObject = new JsonObject();
			jsoObject.addProperty("code", result);
			if (result == ResponseCode.SUCCESS_CODE) {				
				jsoObject.addProperty("jwt", accountController.getJWTOfAccount(account));
			}
				session.getBasicRemote().sendText(
						String.valueOf(jsoObject.toString()));

		} catch (Exception e) {
			e.printStackTrace();
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
