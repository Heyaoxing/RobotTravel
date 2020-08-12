package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: PanoramaResultDto.java
 * @Package com.bugzai.dto.travelImage
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 14:56
 */
@Data
public class PanoramaResultDto implements Serializable {
    private String imageBase64;
    private String byteMd5;
}
