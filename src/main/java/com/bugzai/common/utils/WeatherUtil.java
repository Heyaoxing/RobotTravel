package com.bugzai.common.utils;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.dto.DriveTravalPlanResultDto;
import com.bugzai.common.dto.WeatherDto;
import com.bugzai.common.dto.WeatherResultDto;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: WealthUtil.java
 * @Package com.bugzai.common.utils
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 11:07
 * @Version V1.0
 */
@Component
public class WeatherUtil {

    @Autowired
    private AppConfig appConfig;

    public WeatherResultDto getWeatherInfo(WeatherDto dto){
        Map<String, String> params=new HashMap<>();
        params.put("key",appConfig.getHeWeatherKey());
        params.put("location",dto.getLocation().toString());

        try {
            HttpClientResult httpClientResult = HttpClientUtils.doGet(appConfig.getHeWeatherUrl(),params);
            if(StringUtils.isEmpty(httpClientResult.getContent())){
                return null;
            }

            JsonParser parser = new JsonParser();
            JsonObject root = parser.parse(httpClientResult.getContent()).getAsJsonObject();;
            JsonArray dataJson =   root.getAsJsonArray("HeWeather6");
            Gson gson=new Gson();
            WeatherResultDto resultDto = gson.fromJson(dataJson.get(0),WeatherResultDto.class);
            return resultDto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
