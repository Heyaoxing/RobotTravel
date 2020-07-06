package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: LocationDetailDto.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 16:52
 * @Version V1.0
 */
@Data
public class LocationDetailDto implements Serializable {

    /**
     *
     */
    private String name;

    /**
     * poi经纬度坐标
     */
    private LocationPoint location;

    /**
     * poi地址信息
     */
    private String address;
    /**
     * 所属省份
     */
    private String province;
    /**
     * 所属城市
     */
    private String city;
    /**
     * 所属区县
     */
    private String area;
    /**
     * 街景图id
     */
    private String street_id;
    /**
     * poi的唯一标示，可用于详情检索
     */
    private String uid;
}
