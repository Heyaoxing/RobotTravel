package com.bugzai.machine;

/**
 * @Title: ITransition.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:06
 * @Version V1.0
 */
public abstract class ITransition<TEvent extends Comparable, TState extends Comparable> {
    TState from;
    TEvent event;
    TState to;
}
