package com.bugzai.dto.travelplan;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: MapSteps.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 15:59
 * @Version V1.0
 */
@Data
public class MapSteps implements Serializable {
    /**
     * 距离米
     */
    private Integer distance;
    /**
     * 耗时秒
     */
    private Integer duration;

    /**
     * 分段路况详情
     */
    private String path;

    /**
     * 路段描述
     */
    private String instruction;

}
