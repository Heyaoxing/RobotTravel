package com.bugzai.handler;

import com.bugzai.common.constants.RedisKeyConstants;
import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.RedisWalkStep;
import com.bugzai.common.enums.TravelStatusEnum;
import com.bugzai.common.utils.RedisUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Title: ReadyHandler.java
 * @Package com.bugzai.handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/7 10:57
 * @Version V1.0
 */
@Slf4j
@Component("readyHandler")
public class ReadyHandler extends AbstractHandler {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected HandlerMessage handler(HandlerMessage message) {
        String json = redisUtil.lpop(RedisKeyConstants.TRAVEL_WALK_ID_KEY + message.getTravelId());
        if (StringUtils.isEmpty(json)) {
            message.setPreviousMessage("没有需要位移的路程");
            stopNext(message);
            return message;
        }

        RedisWalkStep redisWalkStep = new Gson().fromJson(json, RedisWalkStep.class);
        message.setTravelStatus(TravelStatusEnum.READY.getStatus());
        message.setNextLocation(new Point(redisWalkStep.getLatitude(),redisWalkStep.getLongitude()));
        message.setTravelCost(redisWalkStep.getDuration());
        message.setDistance(redisWalkStep.getDistance());
        return message;
    }
}
