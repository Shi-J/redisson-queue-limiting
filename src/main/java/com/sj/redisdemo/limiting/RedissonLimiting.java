package com.sj.redisdemo.limiting;

import com.sj.redisdemo.queue.RedissonDelayQueue;
import com.sj.redisdemo.redis.RedisUtils;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shijing
 * @Date 2023/6/15
 * @Classname RedissonLimting
 */
@Component
public class RedissonLimiting {
    @Autowired
    RedisUtils redisUtils;

    //  获取令牌 true得到,false没得到
    public boolean getToken(){
        RRateLimiter rateLimiter = redisUtils.getRedissonClient().getRateLimiter("TOKEN");
        //  一秒内产生一百个
        rateLimiter.trySetRate(RateType.OVERALL, 100, 1,
                RateIntervalUnit.SECONDS);
       return rateLimiter.tryAcquire(1);
    }

}
