package com.bugzai.machine;

/**
 * @Title: State.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 13:52
 * @Version V1.0
 */
public class State<TEvent extends Comparable,TState extends Comparable> extends IState<TEvent,TState> {

    State(TState stateId) {
        this.stateId = stateId;
    }

    private TState stateId;
    Action action;

    public TState getStateId() {
        return stateId;
    }

    public void setStateId(TState stateId) {
        this.stateId = stateId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
