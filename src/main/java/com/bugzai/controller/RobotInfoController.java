package com.bugzai.controller;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.constants.RedisKeyConstants;
import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.RedisRobotInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: RobotInfoController.java
 * @Package com.bugzai.controller
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/20 17:19
 * @Version V1.0
 */
@RestController
@RequestMapping("/robot")
@Slf4j
public class RobotInfoController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AppConfig appConfig;
    @ResponseBody
    @RequestMapping(value = "/getCurrentInfo", method = RequestMethod.GET)
    public RedisRobotInfo getCurrentInfo(){
        String str = stringRedisTemplate.opsForValue().get(RedisKeyConstants.ROBOT_CURRENT_INFO_KEY+appConfig.getRobotCode());
        if(StringUtils.isEmpty(str)){
            return null;
        }

        RedisRobotInfo redisRobotInfo=new Gson().fromJson(str,RedisRobotInfo.class);
        return redisRobotInfo;
    }
}
