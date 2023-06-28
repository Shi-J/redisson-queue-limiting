package com.sj.redisdemo.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * @author shijing
 * @Date 2023/6/27
 * @Classname RedissonController
 */
@RestController
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/testRedisson")
    public String testRedisson(@RequestParam("userId") Long userId)  {
        String lockId = userId + "testRedisson";
        RLock lock = redissonClient.getLock(lockId);
        try {
            System.out.println(UUID.randomUUID().toString()+":"+Thread.currentThread().getId());
            if(lock.tryLock()){

            }
            //  退出程序
//            System.exit(0);

            //
            Thread.sleep(100000);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                System.out.println("锁释放");
                lock.unlock();
            }
        }
        return "testRedisson";
    }

}
