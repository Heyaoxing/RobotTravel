package com.bugzai.controller;

import com.bugzai.common.config.AppConfig;
import com.bugzai.common.constants.RedisKeyConstants;
import com.bugzai.common.dto.RedisRobotInfo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Title: RobotInfoController.java
 * @Package com.bugzai.controller
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/20 17:19
 * @Version V1.0
 */
@RestController
@RequestMapping("/robot")
@Slf4j
public class RobotInfoController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AppConfig appConfig;
    @ResponseBody
    @RequestMapping(value = "/getCurrentInfo", method = RequestMethod.GET)
    public RedisRobotInfo getCurrentInfo(){
        String str = stringRedisTemplate.opsForValue().get(RedisKeyConstants.ROBOT_CURRENT_INFO_KEY+appConfig.getRobotCode());
        if(StringUtils.isEmpty(str)){
            return null;
        }

        RedisRobotInfo redisRobotInfo=new Gson().fromJson(str,RedisRobotInfo.class);
        return redisRobotInfo;
    }

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("verificationCode", createText);
            // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/checkVerificationCode")
    public String checkVerificationCode(@RequestParam(value = "verificationCode") String verificationCode, HttpServletRequest httpServletRequest) {
        String verificationCodeIn = (String) httpServletRequest.getSession().getAttribute("verificationCode");
        httpServletRequest.getSession().removeAttribute("verificationCode");
        if (StringUtils.isEmpty(verificationCodeIn) || !verificationCodeIn.equals(verificationCode)) {
            return "验证码错误，或已失效";
        }
        return "success";
    }
}
