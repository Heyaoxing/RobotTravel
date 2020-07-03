package com.bugzai.dto.travelplan;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: LocationPoint.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 16:53
 * @Version V1.0
 */
@Data
public class LocationPoint implements Serializable {
    private float lat;
    private float lng;
}
