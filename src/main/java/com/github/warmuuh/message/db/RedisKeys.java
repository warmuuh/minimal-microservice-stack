package com.github.warmuuh.message.db;

public class RedisKeys {

	public static String userMessages(String uid){
		return "user:"+uid + ":messages.to";
	}
	
	public static String userMessagesToRelation(String uid, String relId){
		return "user:"+uid + ":messages.to:"+relId;
	}
}
