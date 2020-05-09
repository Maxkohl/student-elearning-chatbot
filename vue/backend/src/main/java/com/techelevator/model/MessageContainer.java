package com.techelevator.model;

import java.util.Arrays;

public class MessageContainer {
	
	
	
	
	//Change this to list of newly created bean to hold each 
	ChatMessage [] messageContainer;

	public ChatMessage[] getMessageContainer() {
		return messageContainer;
	}

	public void setMessageContainer(ChatMessage[] messageContainer) {
		this.messageContainer = messageContainer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(messageContainer);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageContainer other = (MessageContainer) obj;
		if (!Arrays.equals(messageContainer, other.messageContainer))
			return false;
		return true;
	}
	
	

}
