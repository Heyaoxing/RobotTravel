package com.bugzai.machine;

import com.bugzai.common.dto.Point;
import com.bugzai.common.dto.WeatherNowDto;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.Date;

/**
 * @Title: ActionMessage.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/10 18:10
 * @Version V1.0
 */
@Data
public class ActionMessage {

    private IStateMachine stateMachine;

    private String name;

    /**
     * 是否进行下一步
     */
    private Boolean toNext=true;

    /**
     * 状态
     * 1：准备状态
     * 2：进行中
     * 3：完成
     * @see  com.bugzai.common.enums.TravelStatusEnum
     */
    private Integer travelStatus;
    /**
     * 旅途id
     * 每一段旅途id都不一样
     */
    private String travelId;

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
     * 下一个旅行地点距离米
     */
    private Integer distance;

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

    @Override
    public String toString(){
        return String.format("travelId:%s travelStatus:%s currentLocation:%s nextLocation:%s travelCost:%s previousMessage:%s movingTime:%s weather:%s"
        ,travelId,travelStatus,currentLocation,nextLocation,travelCost,previousMessage,new Gson().toJson(weather));
    }
}
