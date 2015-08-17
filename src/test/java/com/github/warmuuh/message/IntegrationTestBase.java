package com.github.warmuuh.message;

import com.github.warmuuh.message.conf.ConfModule;
import com.github.warmuuh.message.db.DatabaseModule;
import com.github.warmuuh.message.metrics.MetricsModule;
import com.github.warmuuh.message.web.WebModule;

public abstract class IntegrationTestBase {

	
	protected static ConfModule config;
	protected static DatabaseModule db;
	protected static WebModule web;
	protected static MetricsModule metrics;
	
	public static void setup(){
		config = new ConfModule();
		db = new DatabaseModule();
		web = new WebModule();
		metrics = new MetricsModule();
	}
	
	public static void startApplication(){
		db.reference(config, metrics);
		web.reference(config, db, metrics);
		
		config.start();
		metrics.start();
		db.start(); 
		web.start();
	}
	
}
