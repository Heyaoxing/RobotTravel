package com.bugzai.common.dto;

import lombok.Data;

/**
 * @Author:
 */
@Data
public class DriveTravalPlanResultDto{

    /**
     * 状态码
     * 0：成功
     * 1：服务内部错误
     * 2：参数无效
     * 7：无返回结果
     */
    private Integer status;

    /**
     * 状态码对应的信息
     */
    private String message;

    private MapResult result;

}
