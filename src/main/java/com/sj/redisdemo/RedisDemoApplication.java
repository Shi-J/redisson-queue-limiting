package com.sj.redisdemo;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@SpringBootApplication
public class RedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}


	@Test
	public void test01(){
		boolean b = true;
		CompletionStage<Boolean> completionStage = CompletableFuture.supplyAsync(()->{
			return b;
		}).thenApply((a)->{
			if(a){
				count(1,2);
			}
			return a;
		});
	}

	public void count(int a,int b){
		int c = a+b;
		System.out.println(c);
	}
}
