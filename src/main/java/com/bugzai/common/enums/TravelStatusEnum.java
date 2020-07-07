package com.bugzai.common.enums;

/**
 * @Title: TravelStatusEnum.java
 * @Package com.bugzai.common.enums
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/7 14:09
 * @Version V1.0
 */
public enum TravelStatusEnum {
    READY(1,"准备"),
    WALKING(1,"进行中"),
    COMPLETE(1,"完成");

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    private Integer status;
    private String desc;

    TravelStatusEnum(Integer status,String desc){
        this.status=status;
        this.desc=desc;
    }
}
