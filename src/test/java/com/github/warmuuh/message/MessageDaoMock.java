package com.github.warmuuh.message;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.github.warmuuh.message.db.MessageDao;
import com.github.warmuuh.message.db.entity.Message;

public class MessageDaoMock extends MessageDao{


		@Override
		public Collection<Message> getMessages(String userId, String relationId, int offset, int count) {
			List<Message> messages  = new  LinkedList<>();
			messages.add(new Message("Test Message 1"));
			messages.add(new Message("Test Message 2"));
			return messages;
		}

		@Override
		public void sendMessage(String userId, String relationId, Message msg) {
		}

		@Override
		public Collection<String> getMessageReceipients(String userId) {
			List<String> receipients  = new  LinkedList<>();
			receipients.add("1");
			receipients.add("2");
			return receipients;
		}
}
