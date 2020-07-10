package com.bugzai.machine;

/**
 * @Title: Action.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 14:52
 * @Version V1.0
 */
@FunctionalInterface
public interface Action  {
    void  Invoke(ActionMessage message);
}
