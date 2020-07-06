package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: WeatherResultDto.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 11:11
 * @Version V1.0
 */
@Data
public class WeatherResultDto implements Serializable {
    private String status;
    private WeatherNowDto now;
}
