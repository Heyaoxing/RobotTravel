package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: MapResultDto.java
 * @Package com.bugzai.dto.travelplan
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 15:48
 * @Version V1.0
 */
@Data
public class MapResult implements Serializable {

    public MapResult(){
        routes=new ArrayList<>();
    }

    /**
     * 起点
     */
    private Point origin;

    /**
     * 终点
     */
    private Point destination;

    private List<MapRoute> routes;
}
