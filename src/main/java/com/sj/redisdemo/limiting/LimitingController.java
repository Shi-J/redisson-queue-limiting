package com.sj.redisdemo.limiting;

import javafx.scene.input.Mnemonic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流
 * @author shijing
 * @Date 2023/6/15
 * @Classname LimitingController
 */
@RestController
@Slf4j
public class LimitingController {

    @Autowired
    RedissonLimiting redissonLimiting;
    static int i = 1;
    @GetMapping(value = "sendMes")
    public void sendMes(@RequestParam("mes") String mes){
        boolean isToken = redissonLimiting.getToken();
        if(isToken){
            log.info("消息发送 :{}",mes +"\t"+i);
        }else{
            log.info("没获取令牌 消息取消发送:{}", mes);
        }
        i++;
    }
}
