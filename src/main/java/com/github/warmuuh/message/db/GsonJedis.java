package com.github.warmuuh.message.db;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.github.warmuuh.message.db.entity.Message;
import com.google.gson.Gson;

import lombok.Data;
import redis.clients.jedis.Jedis;

@Data
public class GsonJedis extends Jedis {

	@Inject
	Gson gson;
	
	public GsonJedis(String host) {
		super(host);
	}


	public void lpush(String msgKey, Object msg) {
		super.lpush(msgKey, serialize(msg));
	}

	public <T> Set<T> zrange(String key, int start, int end, Class<T> type) {
		return deserialize(super.zrange(key, start, end), type);
	}


	public List<Message> lrange(String key, int start, int end, Class<Message> type) {
		return deserialize(super.lrange(key, start, end), type);
	}
	
	
	
	
	
	
	private <T> Set<T> deserialize(Set<String> result, Class<T> type) {
		if (result == null)
			return null;
		
		return result.stream().map(s -> deserialize(s, type)).collect(Collectors.toSet());
	}
	
	private <T> List<T> deserialize(List<String> result, Class<T> type) {
		if (result == null)
			return null;
		
		return result.stream().map(s -> deserialize(s, type)).collect(Collectors.toList());
	}
	private <T> T deserialize(String result,  Class<T> type) {
		if (result == null)
			return null;
		
		return gson.fromJson(result, type);
	}


	private String serialize(Object msg) {
		return gson.toJson(msg);
	}


	
	
	
	
}
