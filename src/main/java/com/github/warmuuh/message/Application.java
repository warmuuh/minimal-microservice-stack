package com.github.warmuuh.message;

import com.github.warmuuh.message.conf.ConfModule;
import com.github.warmuuh.message.db.DatabaseModule;
import com.github.warmuuh.message.metrics.MetricsModule;
import com.github.warmuuh.message.web.WebModule;

public class Application {

	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
				
		ConfModule config = new ConfModule();
		DatabaseModule db = new DatabaseModule();
		WebModule web = new WebModule();
		MetricsModule metrics = new MetricsModule();
		
		db.reference(config, metrics);
		web.reference(config, db, metrics);
		
		config.start();
		metrics.start();
		db.start(); 
		web.start();
		
		long startingTime = System.currentTimeMillis() - starttime;
		System.out.println("Started in " + startingTime + " ms"  );
		
	}
}
