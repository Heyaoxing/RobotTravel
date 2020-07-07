package com.bugzai.handler;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.enums.TravelStatusEnum;
import com.bugzai.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Title: WalkHandler.java
 * @Package com.bugzai.handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 17:51
 * @Version V1.0
 */
@Component("walkHandler")
@Slf4j
public class WalkHandler extends AbstractHandler{

    @Autowired
    private RedisUtil redisUtil;


    @Override
    protected HandlerMessage handler(HandlerMessage message) throws InterruptedException {
        message.setTravelStatus(TravelStatusEnum.WALKING.getStatus());
        TimeUnit.MILLISECONDS.sleep(message.getTravelCost());
        message.setMovingTime(message.getMovingTime()+1);
        message.setCurrentLocation(message.getNextLocation());
        return message;
    }
}
