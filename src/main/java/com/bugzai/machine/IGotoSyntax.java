package com.bugzai.machine;

/**
 * @Title: IGotoSyntax.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:14
 * @Version V1.0
 */
public interface IGotoSyntax<TEvent extends Comparable,TState extends Comparable> {
    IOnSyntax<TEvent, TState> Goto(TState targetState);
}
