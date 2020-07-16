package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: RedisManInfo.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 10:52
 * @Version V1.0
 */
@Data
public class RedisRobotInfo implements Serializable {

    /**
     * 机器编码
     */
    private String code;

    private String name;
    /**
     * 当前位置
     */
    private Point loction;

    /**
     * @see com.bugzai.common.enums.RobotStatus.ActionStatusEnum
     */
    private Integer actionStatue;

    /**
     * @see com.bugzai.common.enums.RobotStatus.BodyStatusEnum
     */
    private Integer bodyStatus;

    /**
     * 更新时间
     */
    private Date updateTm;
}
