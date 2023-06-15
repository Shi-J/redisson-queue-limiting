package com.sj.redisdemo.queue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 延迟队列
 * @author shijing
 * @Date 2023/6/15
 * @Classname QueueController
 */
@RestController
public class QueueController {

    @Autowired
    RedissonDelayQueue redissonDelayQueue;

    @PostMapping("add")
    public void addQueue(@RequestParam("task") String task,@RequestParam("time") Integer time){
        redissonDelayQueue.offerTask(task,time);
    }
}
