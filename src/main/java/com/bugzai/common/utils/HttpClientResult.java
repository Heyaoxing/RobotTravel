package com.bugzai.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: HttpClientResult.java
 * @Package com.bugzai.common.utils
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/2 15:16
 * @Version V1.0
 */
@Data
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code){
        this.code=code;
    }

    public HttpClientResult(int code,String content){
        this.code=code;
        this.content=content;
    }
}
