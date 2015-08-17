package com.github.warmuuh.message.db;

import com.github.warmuuh.message.conf.ConfModule;
import com.github.warmuuh.message.metrics.MetricsModule;
import com.google.gson.Gson;

import wrm.hardwire.Module;

@Module(
	imports={
			ConfModule.class,
			MetricsModule.class
	}
)
public class DatabaseModule extends DatabaseModuleBase {

	@Override
	protected GsonJedis createGsonJedis() {
		String host = refConfModule.getRedisConfiguration().getHost();
		GsonJedis gsonJedis = new GsonJedis(host);
		gsonJedis.setGson(new Gson());
		return gsonJedis;
	}

}
