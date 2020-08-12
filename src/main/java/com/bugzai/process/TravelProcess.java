package com.bugzai.process;

import com.bugzai.common.baidu.BaiduMapUtil;
import com.bugzai.common.config.AppConfig;
import com.bugzai.common.constants.RedisKeyConstants;
import com.bugzai.common.dto.*;
import com.bugzai.common.utils.SpringUtil;
import com.bugzai.handler.AbstractHandler;
import com.bugzai.handler.HandlerMessage;
import com.bugzai.handler.WalkHandler;
import com.bugzai.handler.WeatherHandler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: TravelProcess.java
 * @Package com.bugzai.process
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:24
 * @Version V1.0
 */
@Component("travelProcess")
public class TravelProcess extends AbstractProcess {

    @Autowired
    private BaiduMapUtil baiduMapUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AppConfig appConfig;

    @Override
    protected HandlerMessage wrapMessage() {
        String robotStr = stringRedisTemplate.opsForValue().get(RedisKeyConstants.TRAVEL_PLAN_KEY);
        RedisTravekPlan redisTravekPlan=new Gson().fromJson(robotStr,RedisTravekPlan.class);

        DriveTravalPlanDto dto = new DriveTravalPlanDto();
        dto.setOrigin(redisTravekPlan.getOrigin());
        dto.setDestination(redisTravekPlan.getDestination());

        DriveTravalPlanResultDto resultDto = baiduMapUtil.driveTravalPlan(dto);

        if (Objects.isNull(resultDto) || resultDto.getStatus() != 0) {
            return null;
        }

        String walkKey= RedisKeyConstants.TRAVEL_WALK_ID_KEY + redisTravekPlan.getTravelId();
        AtomicInteger step = new AtomicInteger(1);
        resultDto.getResult().getRoutes().forEach(p -> {
            p.getSteps().forEach(t ->
            {
                String[] paths = StringUtils.delimitedListToStringArray(t.getPath(), ";");
                long cost = t.getDuration() * 1000L / paths.length;
                for (String item : paths) {
                    String[] locations = StringUtils.delimitedListToStringArray(item, ",");
                    RedisWalkStep walkStep = new RedisWalkStep();
                    walkStep.setLongitude(Double.parseDouble(locations[0]));
                    walkStep.setLatitude(Double.parseDouble(locations[1]));
                    walkStep.setStep(step.incrementAndGet());
                    walkStep.setDuration(cost);
                    stringRedisTemplate.opsForList().rightPush(walkKey, new Gson().toJson(walkStep));
                    stringRedisTemplate.expire(walkKey,2, TimeUnit.HOURS);
                }
            });
        });

        HandlerMessage handlerMessage = new HandlerMessage();
        handlerMessage.setCurrentLocation(dto.getOrigin());
        handlerMessage.setTravelId(redisTravekPlan.getTravelId());
        return handlerMessage;
    }

    @Override
    protected AbstractHandler getHandler() {
        AbstractHandler readyHandler = SpringUtil.getBean("readyHandler", WeatherHandler.class);
        AbstractHandler weatherHandler = SpringUtil.getBean("weatherHandler", WeatherHandler.class);
        AbstractHandler walkHandler = SpringUtil.getBean("walkHandler", WalkHandler.class);
        return readyHandler.setNextHandler(weatherHandler).setNextHandler(walkHandler).setNextHandler(readyHandler);
    }
}
