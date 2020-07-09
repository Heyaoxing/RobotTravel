package com.bugzai.machine;

/**
 * @Title: IInSyntax.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:12
 * @Version V1.0
 */
public interface IInSyntax<TEvent extends Comparable,TState extends Comparable> {
    IEnterActionSyntax<TEvent, TState> In(TState state);
}
