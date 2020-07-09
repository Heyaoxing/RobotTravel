package com.bugzai.machine;

/**
 * @Title: IOnSyntax.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:13
 * @Version V1.0
 */
public interface IOnSyntax<TEvent extends Comparable,TState extends Comparable> {
    IGotoSyntax<TEvent,TState> On(TEvent evt);
}
