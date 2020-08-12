package com.bugzai.common.baidu;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.utils.HttpClientResult;
import com.bugzai.common.utils.HttpClientUtils;
import com.bugzai.common.dto.PanoramaDto;
import com.bugzai.common.dto.PanoramaResultDto;
import com.bugzai.common.dto.DriveTravalPlanDto;
import com.bugzai.common.dto.DriveTravalPlanResultDto;
import com.bugzai.common.dto.LocationDto;
import com.bugzai.common.dto.LocationResultDto;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: BaiduMapUtil.java
 * @Package com.bugzai.common.baidu
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 10:23
 * @Version V1.0
 */
@Component("baiduMapUtil")
public class BaiduMapUtil {
    @Autowired
    private AppConfig appConfig;



    /**
     * 驾驶规划
     * @param dto
     * @return
     */
    public DriveTravalPlanResultDto driveTravalPlan(DriveTravalPlanDto dto) {
        Map<String, String> params=new HashMap<>();
        params.put("origin",dto.getOrigin().toString());
        params.put("destination",dto.getDestination().toString());
        params.put("tactics",dto.getTactics().toString());
        params.put("ak",appConfig.getBaiduMapAppkey());
        params.put("gps_direction",dto.getGpsDirection().toString());
        params.put("speed",dto.getSpeed().toString());

        try {
            HttpClientResult httpClientResult = HttpClientUtils.doGet(appConfig.getBaiduDirectionliteUrl(),params);
            if(StringUtils.isEmpty(httpClientResult.getContent())){
                return null;
            }

            Gson gson=new Gson();
            DriveTravalPlanResultDto resultDto = gson.fromJson(httpClientResult.getContent(),DriveTravalPlanResultDto.class);
            return resultDto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 地点检索
     * @param dto
     * @return
     */
    public LocationResultDto searchLocation(LocationDto dto) {
        Map<String, String> params=new HashMap<>();
        params.put("ak",appConfig.getBaiduMapAppkey());
        params.put("query",dto.getQuery());
        params.put("location",dto.getLocation().toString());
        params.put("radius",dto.getRadius().toString());
        params.put("output",dto.getOutput());
        try {
            HttpClientResult httpClientResult = HttpClientUtils.doGet(appConfig.getBaiduSearchLocationUrl(),params);
            if(StringUtils.isEmpty(httpClientResult.getContent())){
                return null;
            }

            Gson gson=new Gson();
            LocationResultDto resultDto = gson.fromJson(httpClientResult.getContent(),LocationResultDto.class);
            return resultDto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



    /**
     * 获取全景图
     * @param dto
     * @return
     */
    public PanoramaResultDto getPanorama(PanoramaDto dto) {
        PanoramaResultDto resultDto=new PanoramaResultDto();
        Map<String, String> params=new HashMap<>();
        params.put("ak",appConfig.getBaiduMapAppkey());
        params.put("location",dto.getLocation().getLongitude()+","+dto.getLocation().getLatitude());
        params.put("width",dto.getWidth().toString());
        params.put("height",dto.getHeight().toString());
        params.put("heading",dto.getHeading().toString());

        try {
            byte[] bytes = HttpClientUtils.downLoadToByte(appConfig.getBaiduPanoramaUrl(),params);
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(bytes);
            resultDto.setByteMd5(new String(Hex.encodeHex(md.digest())));
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(bytes);
            resultDto.setImageBase64(base64);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultDto;
    }
}
