package com.github.warmuuh.message.db.entity;

import lombok.Data;

@Data
public class Message {

	String text;
	long timestamp;
	
	public Message(String text) {
		super();
		this.text = text;
	}
	
	
	
}
