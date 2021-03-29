package com.justEatTakeAway.gameofthree.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.justEatTakeAway.gameofthree.model.GamePlay;

@Controller
public class GameController {

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public GamePlay sendMessage(@Payload final GamePlay play) {
		return play;
	}

	@MessageMapping("/chat.newUser")
	@SendTo("/topic/public")
	public GamePlay newUser(@Payload final GamePlay play,
			SimpMessageHeaderAccessor headerAccessor) {

		System.out.println("headerAccessor:"+headerAccessor.getSessionAttributes());
		headerAccessor.getSessionAttributes().put("username", play.getSender());
		return play;
	}
}
