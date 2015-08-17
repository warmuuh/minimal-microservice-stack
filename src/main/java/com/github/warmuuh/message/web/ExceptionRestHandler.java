package com.github.warmuuh.message.web;

import static spark.Spark.exception;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gson.Gson;

import lombok.Data;
import spark.Request;
import spark.Response;

@Data
@Singleton
public class ExceptionRestHandler {

	
	
	@Inject 
	Gson gson;
	
	@PostConstruct
	public void setup(){
		exception(IllegalArgumentException.class, this::handleException);
	}
	
	
	public void handleException(Exception e, Request req, Response res){
		Map<String, String> result = new HashMap<>();
		result.put("error", e.getMessage());
		res.body(gson.toJson(result));
		res.status(400);
	}
	
	
	
	
	
	
}
