package com.bugzai.common.enums;

import lombok.Getter;

/**
 * @Title: TargetEnum.java
 * @Package com.bugzai.common.enums
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/16 18:28
 * @Version V1.0
 */
public class RobotTarget {
    public enum DirectionEnum {
        SCENIC("景点"),
        HOTEL("酒店");

        @Getter
        private String direction;

        DirectionEnum(String direction) {
            this.direction = direction;
        }
    }
}
