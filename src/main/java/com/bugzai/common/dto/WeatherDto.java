package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: WeatherDto.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 11:09
 * @Version V1.0
 */
@Data
public class WeatherDto implements Serializable {
    /**
     * 经纬度格式：经度,纬度（经度在前纬度在后，英文,分隔，十进制格式，北纬东经为正，南纬西经为负
     */
    private Point location;
}
