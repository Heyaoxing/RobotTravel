package com.bugzai.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: AppConfig.java
 * @Package com.bugzai.common.config
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 15:20
 * @Version V1.0
 */
@Component
public class AppConfig {

    /**
     * 百度地图key
     */
    @Value("${baidu.map.appkey}")
    private String baiduMapAppkey;

    /**
     * 驾车路线规划api
     */
    @Value("${baidu.directionlite.url}")
    private String baiduDirectionliteUrl;


    /**
     * 全景静态图API
     */
    @Value("${baidu.panorama.url}")
    private String baiduPanoramaUrl;

    @Value("${baidu.search.location.url}")
    private String baiduSearchLocationUrl;

    @Value("${heWeather.url}")
    private String heWeatherUrl;
    @Value("${heWeather.key}")
    private String heWeatherKey;

    @Value("${robot.code}")
    private String robotCode;

    @Value("${robot.name}")
    private String robotName;

    @Value("${robot.location}")
    private String robotLocation;

    /**
     * 多少分钟更新一次天气
     */
    @Value("${update.weather.minutes}")
    private Integer updateWeatherMinutes=30;



    public String getHeWeatherUrl() {
        return heWeatherUrl;
    }

    public String getHeWeatherKey() {
        return heWeatherKey;
    }


    public String getBaiduSearchLocationUrl() {
        return baiduSearchLocationUrl;
    }

    public String getBaiduPanoramaUrl() {
        return baiduPanoramaUrl;
    }
    public String getBaiduDirectionliteUrl() {
        return baiduDirectionliteUrl;
    }

    public String getBaiduMapAppkey() {
        return baiduMapAppkey;
    }

    public Integer getUpdateWeatherMinutes() {
        return updateWeatherMinutes;
    }

    public String getRobotCode() {
        return robotCode;
    }
    public String getRobotName() {
        return robotName;
    }
    public String getRobotLocation() {
        return robotLocation;
    }
}
