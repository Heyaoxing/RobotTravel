package com.bugzai;

import com.bugzai.common.baidu.BaiduMapUtil;
import com.bugzai.common.dto.*;
import com.bugzai.common.utils.RedisUtil;
import com.bugzai.common.utils.WeatherUtil;
import com.bugzai.service.TravelPlanService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RobottravelApplicationTests {

    @Autowired
    private BaiduMapUtil baiduMapUtil;

    @Autowired
    private WeatherUtil weatherUtil;

    @Autowired
    private TravelPlanService travelPlanService;


//    @Autowired
//    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void testDriveTravalPlan(){
        DriveTravalPlanDto dto=new DriveTravalPlanDto();
        dto.setOrigin(new Point(40.01116,116.339303));
        dto.setDestination(new Point(39.936404,116.452562));
        DriveTravalPlanResultDto resultDto = baiduMapUtil.DriveTravalPlan(dto);
        Gson gson=new Gson();
        System.out.println(gson.toJson(resultDto));
    }

    @Test
    public void testPanorama(){
        PanoramaDto dto=new PanoramaDto();
        dto.setLocation(new Point(40.04778,116.313393));
        baiduMapUtil.getPanorama(dto);
        System.out.println("test");
    }

    @Test
    public void testSearchLocation(){
        LocationDto dto=new LocationDto();
        dto.setLocation(new Point(39.991903553605,116.38538441268));
        dto.setQuery("酒店");
        LocationResultDto resultDto = baiduMapUtil.searchLocation(dto);
        System.out.println(new Gson().toJson(resultDto));
    }

    @Test
    public void testWeatherInfo(){
        WeatherDto dto=new WeatherDto();
        dto.setLocation(new Point(39.990987727244,116.38564267552));
        WeatherResultDto resultDto =  weatherUtil.getWeatherInfo(dto);
        System.out.println(new Gson().toJson(resultDto));
    }

    @Test
    public void testWalk(){
        travelPlanService.walk();
       System.out.println("end");
    }
//
//    @Test
//    public void test(){
//        redisUtil.set("test","tttt");
//       System.out.println(redisUtil.get("test"));
//    }
}
