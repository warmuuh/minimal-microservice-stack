package com.github.warmuuh.message.web;

import static spark.Spark.after;
import static spark.SparkBase.awaitInitialization;
import static spark.SparkBase.port;

import com.github.warmuuh.message.conf.ConfModule;
import com.github.warmuuh.message.db.DatabaseModule;
import com.github.warmuuh.message.metrics.MetricsModule;
import com.google.gson.Gson;

import wrm.hardwire.Module;



@Module(
	imports={ConfModule.class, DatabaseModule.class, MetricsModule.class},
	external=Gson.class
)
public class WebModule extends WebModuleBase {

	
	@Override
	protected void onStart() {
		port(refConfModule.getWebConfiguration().getPort());
		after((req, res) -> res.type("application/json"));
		after((req, res) -> refMetricsModule.getMetricRegistry()
				.counter("request."+req.requestMethod())
				.inc());
	}
	
	@Override
	protected void onInitialized() {
		awaitInitialization();
	}

	@Override
	protected Gson createGson() {
		return new Gson();
	}

	
}
