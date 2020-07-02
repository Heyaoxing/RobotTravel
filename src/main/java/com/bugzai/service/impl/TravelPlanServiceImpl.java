package com.bugzai.service.impl;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.utils.HttpClientResult;
import com.bugzai.common.utils.HttpClientUtils;
import com.bugzai.dto.travelplan.DriveTravalPlanDto;
import com.bugzai.dto.travelplan.DriveTravalPlanResultDto;
import com.bugzai.service.TravelPlanService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
        import java.util.Map;

/**
 * @Author: bugzai
 */
@Service
public class TravelPlanServiceImpl implements TravelPlanService {
    @Autowired
    private AppConfig appConfig;

    @Override
    public DriveTravalPlanResultDto DriveTravalPlan(DriveTravalPlanDto dto) {
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
}
