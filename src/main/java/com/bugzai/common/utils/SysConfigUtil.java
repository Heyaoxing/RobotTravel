package com.bugzai.common.utils;

import lombok.SneakyThrows;

import java.util.Properties;

/**
 * @Title: SysConfigUtil.java
 * @Package com.bugzai.common.utils
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 18:47
 * @Version V1.0
 */
public class SysConfigUtil {

    private Properties properties;

    public static SysConfigUtil getSysConfigUtil(String s){
        return new SysConfigUtil(s);
    }

    @SneakyThrows
    private SysConfigUtil(String s){
        if (null == s || "".equals(s)) {
            return;
        }
        properties = new Properties();
        properties.load(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(s)
        );

    }

    public String getString(String s) {
        return (properties.getProperty(s));
    }

    public Integer getInt(String s) {
        return Integer.parseInt(properties.getProperty(s));
    }

    public Long getLong(String s) {
        return Long.parseLong(properties.getProperty(s));
    }

    public Boolean getBoolean(String s) {
        return Boolean.parseBoolean(properties.getProperty(s));
    }
}