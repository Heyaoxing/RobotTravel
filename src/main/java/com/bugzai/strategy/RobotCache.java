package com.bugzai.strategy;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.RedisRobotInfo;
import com.bugzai.common.enums.RobotStatus;
import com.bugzai.common.utils.SpringUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Title: RobotCache.java
 * @Package com.bugzai.strategy
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 11:22
 * @Version V1.0
 */
@Slf4j
public class RobotCache {

    private static volatile RobotCache instance;

    public static RobotCache getInstance() {
        if (instance == null) {
            synchronized (RobotCache.class) {
                if (instance == null) {
                    instance = new RobotCache();
                }
            }
        }
        return instance;
    }


    private BlockingQueue<RedisRobotInfo> blockingQueue;
    private StringRedisTemplate stringRedisTemplate;

    private AppConfig appConfig;

    public RobotCache() {
        blockingQueue = new LinkedBlockingDeque<>();
        stringRedisTemplate = SpringUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);
        appConfig = SpringUtil.getBean("appConfig", AppConfig.class);

        new Thread(() -> {
            while (true) {
                try {
                    RedisRobotInfo redisRobotInfo = blockingQueue.take();
                    process(redisRobotInfo);
                } catch (InterruptedException e) {
                    log.error("更新robot异常", e);
                }
            }
        }, "updateRobotCache").start();
    }


    private void process(RedisRobotInfo redisRobotInfo) {
        redisRobotInfo.setUpdateTm(new Date());
        stringRedisTemplate.opsForValue().set(redisRobotInfo.getCode(), new Gson().toJson(redisRobotInfo));
    }

    public  void init() {
        String robotStr = stringRedisTemplate.opsForValue().get(appConfig.getRobotCode());
        if (!StringUtils.isEmpty(robotStr)) {
            return;
        }

        RedisRobotInfo redisRobotInfo = new RedisRobotInfo();
        redisRobotInfo.setCode(appConfig.getRobotCode());
        redisRobotInfo.setName(appConfig.getRobotName());
        redisRobotInfo.setActionStatue(RobotStatus.ActionStatusEnum.DAZE.getStatus());
        redisRobotInfo.setBodyStatus(RobotStatus.BodyStatusEnum.NORMAL.getStatus());
        String[] point = appConfig.getRobotLocation().split(",");
        redisRobotInfo.setLoction(new Point(Double.parseDouble(point[0]),Double.parseDouble(point[1])));
        blockingQueue.add(redisRobotInfo);
    }

    public void updateLocation(Point location) {
        String robotStr = stringRedisTemplate.opsForValue().get(appConfig.getRobotCode());
        RedisRobotInfo redisRobotInfo=new Gson().fromJson(robotStr,RedisRobotInfo.class);
        redisRobotInfo.setLoction(location);
        blockingQueue.add(redisRobotInfo);
    }
}
