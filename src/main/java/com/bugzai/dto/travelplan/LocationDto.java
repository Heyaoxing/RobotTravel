package com.bugzai.dto.travelplan;

import com.bugzai.dto.Point;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: LocationDto.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 16:41
 * @Version V1.0
 */
@Data
public class LocationDto implements Serializable {

    /**
     * 	检索关键字
     */
    private String query;

    /**
     * 圆形区域检索中心点
     * 不支持多个点
     * lat<纬度>,lng<经度>
     */
    private Point location;

    /**
     * 圆形区域检索半径，单位为米。(当半径过大，超过中心点所在城市边界时，会变为城市范围检索，检索范围为中心点所在城市）
     * 	1000（默认）
     */
    private Integer radius=1000;

    /**
     * 输出格式为json或者xml
     */
    private String output="json";
}
