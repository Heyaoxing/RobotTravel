package com.bugzai.handler;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Title: WalkHandler.java
 * @Package com.bugzai.handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 17:51
 * @Version V1.0
 */
@Slf4j
public class WalkHandler extends AbstractHandler{
//    @Autowired
//    private RedisUtil redisUtil;

    @Autowired
    private AppConfig appConfig;

    @Override
    protected HandlerMessage handler(HandlerMessage message) {
//        if(message.getMovingTime()>=3){
//            message.setToNext(false);
//            return message;
//        }
//
//        message.setMovingTime(message.getMovingTime()+1);
//        redisUtil.set("walk",message.getMovingTime().toString());
//        log.info("当前第几步："+redisUtil.get("walk"));
        log.info(appConfig.getHeWeatherKey());
        return message;
    }
}
