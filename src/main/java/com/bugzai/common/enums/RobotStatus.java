package com.bugzai.common.enums;

import lombok.Getter;

/**
 * @Title: RobotStatus.java
 * @Package com.bugzai.common.enums
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 11:08
 * @Version V1.0
 */
public class RobotStatus {


    public enum ActionStatusEnum {

        SLEEP(0, "睡觉"),
        REST(1, "休息"),
        WALK(2, "走路"),
        EAT(3, "吃饭");

        @Getter
        private Integer status;
        @Getter
        private String desc;

        ActionStatusEnum(Integer status, String desc) {
            this.status = status;
            this.desc = desc;
        }

    }

    public enum BodyStatusEnum{
        NORMAL(0, "正常"),
        HUNGRY(1, "饿了"),
        TRIED(2, "累了"),
        SLEEPY(3, "困了");

        @Getter
        private Integer status;
        @Getter
        private String desc;

        BodyStatusEnum(Integer status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }
}
