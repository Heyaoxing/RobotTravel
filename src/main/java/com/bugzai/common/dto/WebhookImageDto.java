package com.bugzai.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: WebhookImageDto.java
 * @Package com.bugzai.common.dto
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/8/12 17:00
 * @Version V1.0
 */
@Data
public class WebhookImageDto implements Serializable {
    private String msgtype="image";
    private  WebhookImage image;

    public WebhookImageDto(String base64,String md5){
        image=new WebhookImage();
        image.setBase64(base64);
        image.setMd5(md5);
    }
}

@Data
class  WebhookImage implements Serializable{
    private String base64;
    private String md5;
}
