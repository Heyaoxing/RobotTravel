package com.bugzai.handler;

import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.WeatherNowDto;
import com.bugzai.process.BaseProcess;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: HandlerMessage.java
 * @Package handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:11
 * @Version V1.0
 */
@Data
public class HandlerMessage implements Serializable {

    /**
     * 当前位置
     */
    private Point currentLocation;

    /**
     * 下一个旅行地点
     */
    private Point nextLocation;

    /**
     * 下一个旅行地点所需耗时
     */
    private Long travelCost;
    /**
     * 是否进行下一步
     */
    private Boolean toNext=true;
    /**
     * 上一步传递的信息
     */
    private String previousMessage;

    /**
     * 当前连续位移了多少次
     */
    private Integer movingTime=0;


    /**
     * 最新更新天气的时间
     */
    private Date updateWeatherTm=new Date();

    /**
     * 是否需要更新天气
     */
    private Boolean doWeather=true;

    /**
     * 上一次天气信息
     */
    private WeatherNowDto weather;
}
