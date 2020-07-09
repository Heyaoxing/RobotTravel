package com.bugzai.machine;

/**
 * @Title: IEnterActionSyntax.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:13
 * @Version V1.0
 */
public interface IEnterActionSyntax<TEvent extends Comparable,TState extends Comparable> extends  IOnSyntax<TEvent,TState> {
    IOnSyntax<TEvent,TState> OnEnterState(Action enterAction);
}
