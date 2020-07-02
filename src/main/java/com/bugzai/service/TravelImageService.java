package com.bugzai.service;

import com.bugzai.dto.travelImage.PanoramaDto;
import com.bugzai.dto.travelImage.PanoramaResultDto;

/**
 * @Author: 002954
 */
public interface TravelImageService {

    /**
     * 获取全景图
     * @param dto
     * @return
     */
    PanoramaResultDto getPanorama(PanoramaDto dto);

}
