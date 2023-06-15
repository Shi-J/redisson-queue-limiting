package com.sj.redisdemo;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}
}
