package com.github.warmuuh.message.conf;

import javax.inject.Singleton;

import lombok.Data;

@Data
@Singleton
public class RedisConfiguration {

	String host;
}
