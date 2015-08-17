package com.github.warmuuh.message.db;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import lombok.Data;
import redis.clients.jedis.Jedis;

@Data
@Singleton
public class DatabaseHealthCheck extends HealthCheck {

	@Inject
	HealthCheckRegistry healthChecks;
	
	@Inject
	Jedis jedis;

	@Override
	protected Result check() throws Exception {
		jedis.info();
		return Result.healthy();
	}
	
	@PostConstruct
	public void setup(){
		healthChecks.register("redis", this);
	}
	
	
	
	
	
}
