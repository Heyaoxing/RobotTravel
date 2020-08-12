package com.bugzai.common.dto;

import lombok.Data;

/**
 * @Author:
*/
@Data
public class Point {
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;


    public Point(double latitude,double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }

    @Override
    public String toString(){
        return latitude+","+longitude;
    }
}
