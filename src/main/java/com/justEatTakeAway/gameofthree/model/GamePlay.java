package com.justEatTakeAway.gameofthree.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GamePlay {

	private MessageType type;
	private String content ;
	private String sender;
	private String responder;
	private String time;
}
