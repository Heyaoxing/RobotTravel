package com.bugzai.handler;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.WeatherDto;
import com.bugzai.common.dto.WeatherNowDto;
import com.bugzai.common.dto.WeatherResultDto;
import com.bugzai.common.utils.DateUtil;
import com.bugzai.common.utils.WeatherUtil;
import com.bugzai.machine.ActionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @Title: WeatherHandler.java
 * @Package handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:39
 * @Version V1.0
 */
@Slf4j
@Component("weatherHandler")
public class WeatherHandler extends AbstractHandler {

    @Autowired
    private WeatherUtil weatherUtil;
    @Autowired
    private AppConfig appConfig;

    @Override
    protected ActionMessage handler(ActionMessage message) {
        if (!message.getDoWeather()) {
            return message;
        }
        Date now = new Date();
        //判断是否到达时间间隔
        int minuteBetween = DateUtil.getMinuteBetween(now,message.getUpdateWeatherTm());
        if(minuteBetween<appConfig.getUpdateWeatherMinutes()){
            return message;
        }

        message.setDoWeather(false);
        WeatherDto dto = new WeatherDto();
        dto.setLocation(message.getCurrentLocation());
        WeatherResultDto resultDto = weatherUtil.getWeatherInfo(dto);
        if (Objects.isNull(resultDto) || Objects.isNull(resultDto.getNow())) {
            return message;
        }
        message.setWeather(resultDto.getNow());
        message.setUpdateWeatherTm(new Date());
        return message;
    }
}
