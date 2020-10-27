package com.elk.model;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class MessageTemplate {
	
	private String message;
	private String messageId;

	public MessageTemplate(String message, String messageId) {
		super();
		this.message = message;
		this.messageId = messageId;
	}

	@Override
	public String toString() {
		return "MessageTemplate [message=" + message + ", messageId=" + messageId + "]";
	}
		
	
}
