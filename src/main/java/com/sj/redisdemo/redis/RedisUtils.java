package com.sj.redisdemo.redis;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author shijing
 * @Date 2023/6/15
 * @Classname RedisUtils
 */
@Component
@Data
public class RedisUtils {
    private RedissonClient redissonClient;
    @PostConstruct
    public void init() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://localhost:6379");
        redissonClient = Redisson.create(config);
    }
}
