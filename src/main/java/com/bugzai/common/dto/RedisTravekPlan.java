package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: RedisTravekPlan.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 18:46
 * @Version V1.0
 */
@Data
public class RedisTravekPlan implements Serializable {
    /**
     * 起点
     */
    private Point origin;

    /**
     * 终点
     */
    private Point destination;

    private  String travelId;
}
