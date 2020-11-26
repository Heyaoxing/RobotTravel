package com.bugzai;

import com.bugzai.common.baidu.BaiduMapUtil;
import com.bugzai.common.dto.*;
import com.bugzai.common.utils.DateUtil;
import com.bugzai.common.utils.HttpClientUtils;
import com.bugzai.common.utils.WeatherUtil;
import com.bugzai.service.TravelPlanService;
import com.google.gson.Gson;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@SpringBootTest
class RobottravelApplicationTests {

    @Autowired
    private BaiduMapUtil baiduMapUtil;

    @Autowired
    private WeatherUtil weatherUtil;

    @Autowired
    private TravelPlanService travelPlanService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testDriveTravalPlan() {
        DriveTravalPlanDto dto = new DriveTravalPlanDto();
        dto.setOrigin(new Point(40.01116, 116.339303));
        dto.setDestination(new Point(39.936404, 116.452562));
        DriveTravalPlanResultDto resultDto = baiduMapUtil.driveTravalPlan(dto);
        Gson gson = new Gson();
        System.out.println(gson.toJson(resultDto));
    }

    @Test
    public void testPanorama() throws NoSuchAlgorithmException {
        PanoramaDto dto = new PanoramaDto();
        dto.setLocation(new Point(40.04778, 116.313393));
        PanoramaResultDto resultDto = baiduMapUtil.getPanorama(dto);
        String temple = "{\"msgtype\":\"image\",\"image\":{\"base64\":\"%s\",\"md5\":\"%s\"}}";
        String content = String.format(temple, resultDto.getImageBase64(), resultDto.getByteMd5());
        String result = HttpClientUtils.doPostByJson("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=305df922-5ab5-4870-9d13-6664f340c65b", content);
        System.out.println(content + result);
    }

    @Test
    public void testSearchLocation() {
        LocationDto dto = new LocationDto();
        dto.setLocation(new Point(39.991903553605, 116.38538441268));
        dto.setQuery("景点");
        LocationResultDto resultDto = baiduMapUtil.searchLocation(dto);
        System.out.println(new Gson().toJson(resultDto));
    }

    @Test
    public void testWeatherInfo() {
        WeatherDto dto = new WeatherDto();
        dto.setLocation(new Point(39.990987727244, 116.38564267552));
        WeatherResultDto resultDto = weatherUtil.getWeatherInfo(dto);
        System.out.println(new Gson().toJson(resultDto));
    }

    @Test
    public void testWalk() {
        travelPlanService.walk();
        System.out.println("end");
    }

    @Test
    public void test01() {

        for (int i = 0; i < 5; i++) {
            stringRedisTemplate.opsForList().rightPush("queue", String.valueOf(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(stringRedisTemplate.opsForList().leftPop("queue"));
        }
        System.out.println(stringRedisTemplate.opsForValue().get("end"));
    }

    @Test
    public void test() {
        stringRedisTemplate.opsForSet().add("tta", "");
        System.out.println(stringRedisTemplate.hasKey("tta"));
        Set<String> sets = stringRedisTemplate.opsForSet().members("tta");
        List<String> list= new ArrayList<>(sets);
        list.removeIf(String::isEmpty);
        System.out.println(new Gson().toJson(list));
    }

    @Test
    public void test02(){
        Date now=new Date();
        Date after =new Date(2020,01,30);
       System.out.println(DateUtil.getSecondsBetween(after,now));
    }

}
