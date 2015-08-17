package com.github.warmuuh.message.conf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

import wrm.hardwire.Module;

@Module(
		external={
				Config.class
		}
) 
public class ConfModule extends ConfModuleBase {

	@Override
	protected RedisConfiguration createRedisConfiguration() {
		return ConfigBeanFactory.create(getConfig().getConfig("redis"), RedisConfiguration.class);
	}

	
	@Override
	protected WebConfiguration createWebConfiguration() {
		return ConfigBeanFactory.create(getConfig().getConfig("server"), WebConfiguration.class);
	}
	
	@Override
	protected Config createConfig() {
		return ConfigFactory.load();
	}


}
