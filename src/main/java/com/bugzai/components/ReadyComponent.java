package com.bugzai.components;

import com.bugzai.common.constants.RedisKeyConstants;
import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.RedisWalkStep;
import com.bugzai.common.enums.BaseEvent.ReadyActionEnum;
import com.bugzai.common.enums.RobotStatus;
import com.bugzai.common.enums.TravelStatusEnum;
import com.bugzai.machine.Action;
import com.bugzai.machine.ActionMessage;
import com.bugzai.strategy.RobotCache;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ReadyComponent.java
 * @Package com.bugzai.components
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/13 14:44
 * @Version V1.0
 */
@Component("readyComponent")
@Slf4j
public class ReadyComponent implements InitializingBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Map<ReadyActionEnum, Action> readyAction = new HashMap<>();

    public Map<ReadyActionEnum, Action> getReadyAction() {
        return readyAction;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        readyAction.put(ReadyActionEnum.INTI, this::init);
        readyAction.put(ReadyActionEnum.START, this::start);
        readyAction.put(ReadyActionEnum.RUN, this::run);
        readyAction.put(ReadyActionEnum.STOP, this::stop);
    }

    public void init(ActionMessage actionMessage) {
        System.out.println("初始化");
        actionMessage.getStateMachine().fire(ReadyActionEnum.START);
    }

    public void start(ActionMessage actionMessage) {
        System.out.println("开始");
        actionMessage.getStateMachine().fire(ReadyActionEnum.RUN);
    }


    public void run(ActionMessage message) {
        System.out.println("运行");
        String json = stringRedisTemplate.opsForList().leftPop(RedisKeyConstants.TRAVEL_WALK_ID_KEY + message.getTravelId());
        if (StringUtils.isEmpty(json)) {
            message.getStateMachine().fire(ReadyActionEnum.STOP);
            return;
        }
        try {
            RedisWalkStep redisWalkStep = new Gson().fromJson(json, RedisWalkStep.class);
            message.setTravelStatus(TravelStatusEnum.READY.getStatus());
            message.setNextLocation(new Point(redisWalkStep.getLatitude(), redisWalkStep.getLongitude()));
            message.setTravelCost(redisWalkStep.getDuration());
            message.setDistance(redisWalkStep.getDistance());
        } catch (Exception e) {
            log.error(json, e);
        }

    }

    public void stop(ActionMessage message) {
        message.setPreviousMessage("没有需要位移的路程");
        message.setToNext(false);
        RobotCache.getInstance().updateActionStatus(RobotStatus.ActionStatusEnum.REST);
    }
}
