package com.github.warmuuh.message.web;

import static com.github.warmuuh.message.web.util.SparkRequestUtil.getParameter;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.warmuuh.message.db.MessageDao;
import com.github.warmuuh.message.db.entity.Message;
import com.google.gson.Gson;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;

@Slf4j
@Data
@Singleton
public class MessageController {

	
	@Inject
	MessageDao messages;

	@Inject
	Gson gson;
	
	@PostConstruct
	public void setup(){
		get("user/:user/messages/:relation", this::retrieveMessages, gson::toJson);
		get("user/:user/messages", this::getMessageReceipients, gson::toJson);
		post("user/:user/messages/:relation", this::sendMessage, gson::toJson);
	}
	
	
	
	public Collection<Message> retrieveMessages(Request req, Response res){
		
		String userId = getParameter(req, "user");
		String relationId = getParameter(req, "relation");
		int offset = getParameter(req, "offset", 0, o -> o > 0);
		int count = getParameter(req, "count", 10, c -> c > 1);
		
		log.info("fetching messages for {} -> {}", userId, relationId);
		
		return messages.getMessages(userId, relationId, offset, count);
	} 
	
	public Message sendMessage(Request req, Response res){
		String userId = getParameter(req, "user");
		String relationId = getParameter(req, "relation");
		
		log.info("sending message {} -> {}", userId, relationId);
		
		Message msg = gson.fromJson(req.body(), Message.class);
		messages.sendMessage(userId, relationId, msg);
		return msg;
	}
	
	public Collection<String> getMessageReceipients(Request req, Response res){
		String userId = getParameter(req, "user");
		log.info("get message receipients for {}", userId);
		return messages.getMessageReceipients(userId);
	}
	
}
