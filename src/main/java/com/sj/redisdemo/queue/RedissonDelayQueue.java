package com.sj.redisdemo.queue;

import com.sj.redisdemo.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author shijing
 * @Date 2023/6/15
 * @Classname RedissonDelayQueue
 */

@Component
@Slf4j
public class RedissonDelayQueue {

    @Autowired
    RedisUtils redisUtils;
    private RDelayedQueue<String> delayQueue;
    private RBlockingQueue<String> blockingQueue;

    @PostConstruct
    public void init() {
        initDelayQueue();
        startDelayQueueConsumer();
    }

    private void initDelayQueue() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://localhost:6379");
        //   阻塞队列
        blockingQueue = redisUtils.getRedissonClient().getBlockingQueue("SANYOU");
        //  延迟队列
        delayQueue = redisUtils.getRedissonClient().getDelayedQueue(blockingQueue);
    }

    private void startDelayQueueConsumer() {
        new Thread(() -> {
            while (true) {
                try {
                    String task = blockingQueue.take();
                    log.info("接收到延迟任务:{}", task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "SANYOU-Consumer").start();
    }

    public void offerTask(String task, long seconds) {
        log.info("添加延迟任务:{} 延迟时间:{}s", task, seconds);
        delayQueue.offer(task, seconds, TimeUnit.SECONDS);
    }

}
