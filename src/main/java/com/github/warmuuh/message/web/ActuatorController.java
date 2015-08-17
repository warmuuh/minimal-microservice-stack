package com.github.warmuuh.message.web;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.google.gson.Gson;
import com.typesafe.config.Config;

import lombok.Data;
import spark.Request;
import spark.Response;

@Data
@Singleton
public class ActuatorController {

	@Inject
	HealthCheckRegistry healthChecks;
	
	@Inject
	MetricRegistry metrics;
	
	@Inject
	Config config;
	
	@Inject
	Gson gson;
	
	@PostConstruct
	public void setup(){
		get("/health", (req, res) -> healthChecks.runHealthChecks(), gson::toJson);
		get("/metrics", this::renderMetrics, gson::toJson);
		get("/env", (req,res) -> config.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().unwrapped())), gson::toJson);
		get("/env/:property", (req,res) -> config.getAnyRef(req.params(":property")), gson::toJson);
	}
	
	
	public Map<String, Object> renderMetrics(Request req, Response res) throws Exception {
		Map<String, Object> result = new HashMap<>();
		metrics.getCounters().forEach((k,c) -> result.put("counter."+k, c.getCount()));
		metrics.getGauges().forEach((k,g) -> result.put("gauge."+k, g.getValue()));
		metrics.getMeters().forEach((k,m) -> result.put("meters."+k, m.getCount()));
		return result;
	}
	
	
}
