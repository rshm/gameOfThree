package com.justEatTakeAway.gameofthree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.justEatTakeAway.gameofthree.model.GamePlay;
import com.justEatTakeAway.gameofthree.model.MessageType;

import lombok.extern.slf4j.Slf4j;

/**
 * Listens for the user connecting and disconnecting events
 */
@Component
@Slf4j
public class WebSocketEventListener {

	@Autowired
	private SimpMessageSendingOperations sendingOperations;
	@EventListener
	public void handleWebSocketConnectListener(final SessionConnectedEvent event){

		log.info("YAAAAAAAY! New player connected");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event){
		final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		System.out.println("session attrib:"+  headerAccessor.getSessionAttributes());
		final String username = (String) headerAccessor.getSessionAttributes().get("username");
		final GamePlay gameMessage =  GamePlay.builder()
				.type(MessageType.DISCONNECT)
				.sender(username)
				.build();

		sendingOperations.convertAndSend("/topic/public",gameMessage);
	}



}
