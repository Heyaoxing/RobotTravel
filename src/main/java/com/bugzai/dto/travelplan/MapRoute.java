package com.bugzai.dto.travelplan;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: MapRoute.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 15:50
 * @Version V1.0
 */
@Data
public class MapRoute implements Serializable {

    public MapRoute(){
        steps=new ArrayList<>();
    }
    /**
     * 距离米
     */
    private Integer distance;

    /**
     * 耗时秒
     */
    private Integer duration;

    private List<MapSteps> steps;
}
