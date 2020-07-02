package com.bugzai.dto.travelImage;

import com.bugzai.dto.Point;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: PanoramaDto.java
 * @Package com.bugzai.dto.travelImage
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 14:56
 */
@Data
public class PanoramaDto implements Serializable {
    /**
     * 全景位置点坐标。坐标格式：lng<经度>，lat<纬度>，例如116.313393,40.047783。
     */
    private Point location;
    /**
     * 图片宽度，范围[10,1024]
     */
    private Integer width=1024;
    /**
     * 图片高度，范围[10,512]
     */
    private Integer height=512;
    /**
     * 水平视角，范围[0,360]
     */
    private Integer heading=0;
}
