package com.bugzai.strategy;

import com.bugzai.common.baidu.BaiduMapUtil;
import com.bugzai.common.constants.RedisKeyConstants;
import com.bugzai.common.dto.*;
import com.bugzai.common.enums.RobotStatus;
import com.bugzai.common.enums.RobotTarget;
import com.bugzai.common.utils.SpringUtil;
import com.bugzai.service.TravelPlanService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @Title: TravelStrategy.java
 * @Package com.bugzai.strategy
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 17:26
 * @Version V1.0
 */
@Slf4j
public class RobotStrategy {
    private static volatile RobotStrategy instance;

    public static RobotStrategy getInstance() {
        if (instance == null) {
            synchronized (RobotStrategy.class) {
                if (instance == null) {
                    instance = new RobotStrategy();
                }
            }
        }
        return instance;
    }

    private ExecutorService executor = new ThreadPoolExecutor(2,
            10,
            100L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    private BlockingQueue<RedisRobotInfo> blockingQueue = new LinkedBlockingDeque<>();


    private BaiduMapUtil baiduMapUtil;
    private TravelPlanService travelPlanService;
    private RedisTemplate<Object, Object> redisTemplate;
    private StringRedisTemplate stringRedisTemplate;

    public RobotStrategy() {
        baiduMapUtil = SpringUtil.getBean("baiduMapUtil", BaiduMapUtil.class);
        stringRedisTemplate = SpringUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);
        redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        travelPlanService = SpringUtil.getBean("travelPlanService", TravelPlanService.class);

        executor.execute(() -> {
            while (true) {
                try {
                    RedisRobotInfo robotInfo = blockingQueue.take();
                    if (RobotStatus.ActionStatusEnum.REST.getStatus().equals(robotInfo.getActionStatue())) {
                        process(robotInfo);
                    }
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            }
        });
    }

    private void process(RedisRobotInfo robotInfo) {
        LocationDto dto = new LocationDto();
        dto.setLocation(robotInfo.getLoction());
        dto.setQuery(RobotTarget.DirectionEnum.SCENIC.getDirection());
        LocationResultDto resultDto = baiduMapUtil.searchLocation(dto);
        if (Objects.isNull(resultDto) || CollectionUtils.isEmpty(resultDto.getResults())) {
            return;
        }


        int i=5;
        while (i>0){
            i--;
            int index = (int) (Math.random() * resultDto.getResults().size());
            LocationDetailDto detailDto = resultDto.getResults().get(index);
            LocationPoint locationPoint = detailDto.getLocation();
            GeoResults<RedisGeoCommands.GeoLocation<Object>> getoResults = redisTemplate.opsForGeo().radius(RedisKeyConstants.TRAVEL_HISTORY_POINT_KEY, new Circle(locationPoint.getLng(), locationPoint.getLat(), 500));
            if (Objects.isNull(getoResults)||CollectionUtils.isEmpty(getoResults.getContent())) {
                String travelId = UUID.randomUUID().toString().replace("-", "");
                RedisTravekPlan plan = new RedisTravekPlan();
                plan.setTravelId(travelId);
                plan.setOrigin(robotInfo.getLoction());
                plan.setDestination(new Point(locationPoint.getLat(), locationPoint.getLng()));
                stringRedisTemplate.opsForValue().set(RedisKeyConstants.TRAVEL_PLAN_KEY, new Gson().toJson(plan));
                redisTemplate.opsForGeo().add(RedisKeyConstants.TRAVEL_HISTORY_POINT_KEY, new org.springframework.data.geo.Point(locationPoint.getLng(), locationPoint.getLat()), detailDto.getName());
                log.info("开始旅行 地点:{},地址:{}", detailDto.getName(), detailDto.getAddress());

                executor.execute(() -> {
                    travelPlanService.walk();
                });
                break;
            }
        }
    }

    public void updateActionStatus(RedisRobotInfo redisRobotInfo) {
        try {
            TimeUnit.SECONDS.sleep(5);
            blockingQueue.add(redisRobotInfo);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
