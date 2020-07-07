package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: RedisWalkStep.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/7 11:42
 * @Version V1.0
 */
@Data
public class RedisWalkStep implements Serializable {
    /**
     * 距离米
     */
    private Integer distance;
    /**
     * 耗时毫秒
     */
    private Long duration;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;

    /**
     * 当前序号
     */
    private Integer step;
}
