package com.bugzai.dto.travelplan;

import com.bugzai.dto.Point;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 002954
 */
@Data
public class DriveTravalPlanDto {
    /**
     * 起点
     */
    private Point origin;

    /**
     * 终点
     */
    private Point destination;

    /**
     * 路线偏好
     * 	默认值：0 常规路线，即多数用户常走的一条经验路线，满足大多数场景需求，是较推荐的一个策略
     * 1：不走高速
     * 2：躲避拥堵
     * 3：距离较短
     */
    private Integer tactics=0;

    /**
     * 起点的定位方向
     * 车头方向为与正北方向顺时针夹角，取值范围[0,359]。
     */
    private Integer gpsDirection=0;

    /**
     * 配合gps_direction字段使用，单位：米/秒
     * 当speed>1.5米/秒且gps_direction存在时，采用gps_direction的方向
     */
    private Integer speed=1;
}
