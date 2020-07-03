package com.bugzai;

import com.bugzai.dto.Point;
import com.bugzai.dto.travelImage.PanoramaDto;
import com.bugzai.dto.travelplan.DriveTravalPlanDto;
import com.bugzai.dto.travelplan.DriveTravalPlanResultDto;
import com.bugzai.dto.travelplan.LocationDto;
import com.bugzai.dto.travelplan.LocationResultDto;
import com.bugzai.service.TravelImageService;
import com.bugzai.service.TravelPlanService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RobottravelApplicationTests {

    @Autowired
    private TravelPlanService travelPlanService;

    @Autowired
    private TravelImageService travelImageService;
    @Test
    void contextLoads() {
    }

    @Test
    public void testDriveTravalPlan(){
        DriveTravalPlanDto dto=new DriveTravalPlanDto();
        dto.setOrigin(new Point(40.01116,116.339303));
        dto.setDestination(new Point(39.936404,116.452562));
        DriveTravalPlanResultDto resultDto = travelPlanService.DriveTravalPlan(dto);
        Gson gson=new Gson();
        System.out.println(gson.toJson(resultDto));
    }

    @Test
    public void testPanorama(){
        PanoramaDto dto=new PanoramaDto();
        dto.setLocation(new Point(40.04778,116.313393));
        travelImageService.getPanorama(dto);
        System.out.println("test");
    }

    @Test
    public void testSearchLocation(){
        LocationDto dto=new LocationDto();
        dto.setLocation(new Point(39.991903553605,116.38538441268));
        dto.setQuery("酒店");
        LocationResultDto resultDto = travelPlanService.searchLocation(dto);
        System.out.println(new Gson().toJson(resultDto));
    }
}
