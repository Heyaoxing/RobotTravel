package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: WeatherNowDto.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 11:12
 * @Version V1.0
 */
@Data
public class WeatherNowDto implements Serializable {
    /**
     *
     */
    private String cloud;
    /**
     *实况天气状况代码
     */
    private String cond_code;
    /**
     *	实况天气状况描述
     */
    private String cond_txt;
    /**
     * 	体感温度，默认单位：摄氏度
     */
    private String fl;
    /**
     *	相对湿度
     */
    private String hum;
    /**
     *	降水量
     */
    private String pcpn;
    /**
     *	大气压强
     */
    private String pres;
    /**
     *	温度，默认单位：摄氏度
     */

    private String tmp;
    /**
     *	能见度，默认单位：公里
     */
    private String vis;
    /**
     *	风向360角度
     */
    private String wind_deg;
    /**
     *	风向
     */
    private String wind_dir;
    /**
     *	风力
     */
    private String wind_sc;
    /**
     *	风速，公里/小时
     */
    private String wind_spd;
}
