package com.bugzai.service.impl;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.utils.HttpClientUtils;
import com.bugzai.dto.travelImage.PanoramaDto;
import com.bugzai.dto.travelImage.PanoramaResultDto;
import com.bugzai.service.TravelImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 002954
 */
@Service
public class TravelImageServiceImpl implements TravelImageService {
    @Autowired
    private AppConfig appConfig;

    @Override
    public PanoramaResultDto getPanorama(PanoramaDto dto) {
        Map<String, String> params=new HashMap<>();
        params.put("ak",appConfig.getBaiduMapAppkey());
        params.put("location",dto.getLocation().getLongitude()+","+dto.getLocation().getLatitude());
        params.put("width",dto.getWidth().toString());
        params.put("height",dto.getHeight().toString());
        params.put("heading",dto.getHeading().toString());

        try {
            byte[] bytes = HttpClientUtils.downLoad(appConfig.getBaiduPanoramaUrl(),"bbb.jpg",params);
            FileOutputStream fout = new FileOutputStream("sss.jpeg");
            //将字节写入文件
            fout.write(bytes);
            fout.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
