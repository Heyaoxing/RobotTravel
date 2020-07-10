package com.bugzai.machine;

import lombok.Data;

/**
 * @Title: ActionMessage.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/10 18:10
 * @Version V1.0
 */
@Data
public class ActionMessage {
    private IStateMachine stateMachine;
    private String name;
}
