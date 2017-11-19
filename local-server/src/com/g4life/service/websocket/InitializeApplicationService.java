package com.g4life.service.websocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.g4life.common.ResponseCode;

@ServerEndpoint("/InitialApp")
public class InitializeApplicationService {
	/** The queue. */
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

	/** The syn object. */
	private static Object synObject = new Object();
	@OnOpen
	public void open(Session session) {
		queue.add(session);
		

	}
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		
	}
	@OnError
	public void error(Session session, Throwable t) {

		queue.remove(session);

	}
	@OnClose
	public void closedConnection(Session session) {

		queue.remove(session);

	}
}
