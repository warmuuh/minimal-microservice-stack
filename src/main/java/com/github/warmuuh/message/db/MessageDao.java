package com.github.warmuuh.message.db;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.codahale.metrics.MetricRegistry;
import com.github.warmuuh.message.db.entity.Message;

import lombok.Data;

@Data
@Singleton
public class MessageDao {

	@Inject 
	GsonJedis jedis;
	
	@Inject
	MetricRegistry registry;
	
	public Collection<Message> getMessages(String userId, String relationId,  int offset, int count){
		registry.counter("db.getMessages").inc();
		String key = RedisKeys.userMessagesToRelation(userId, relationId);
		List<Message> messages = jedis.lrange(key, offset, offset+count-1, Message.class);
		return messages;
	}
	
	public void sendMessage(String userId, String relationId, Message msg){
		registry.counter("db.sendMessage").inc();
		String key = RedisKeys.userMessages(userId);
		String msgKey = RedisKeys.userMessagesToRelation(userId, relationId);
		msg.setTimestamp(System.currentTimeMillis());
		
		jedis.sadd(key, relationId);
		jedis.lpush(msgKey, msg);
	}
	
	
	public Collection<String> getMessageReceipients(String userId){
		registry.counter("db.getMessageReceipients").inc();
		String key = RedisKeys.userMessages(userId);
		return jedis.smembers(key);
	}
	
	
}
