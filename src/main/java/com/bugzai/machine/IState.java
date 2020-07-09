package com.bugzai.machine;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Title: IState.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 11:03
 * @Version V1.0
 */
public abstract class IState<TEvent extends Comparable,TState extends Comparable> {

    TState stateId;

    Action EnterAction;
}
