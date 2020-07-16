package com.bugzai.machine;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: IStateMachine.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:02
 * @Version V1.0
 */
public abstract class IStateMachine<TEvent extends Comparable, TState extends Comparable> {

    String name;

    public abstract IStateMachine init(ActionMessage actionMessage);

    public abstract boolean fire(TEvent event);

    public abstract void reset();

    public abstract void start(TState initState);


    protected  TState CurrentStateId;

    public TState getCurrentStateId() {
        return CurrentStateId;
    }




    Map<TState, Map<TEvent, Transition<TEvent, TState>>> TransitionRules=new HashMap<>();

    Map<TState, IState<TEvent, TState>> States = new HashMap<>();

}


