package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: LocationResultDto.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 16:42
 * @Version V1.0
 */
@Data
public class LocationResultDto implements Serializable {
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

    private List<LocationDetailDto> results;
}
