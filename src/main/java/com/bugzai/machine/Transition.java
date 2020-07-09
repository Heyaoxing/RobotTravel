package com.bugzai.machine;

/**
 * @Title: Transition.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:39
 * @Version V1.0
 */
public class Transition<TEvent  extends Comparable, TState  extends Comparable>  extends ITransition<TEvent , TState>  {

    public Transition(TState fromState, TEvent onEvent, TState targetState)
    {
        this.from = fromState;
        this.to = targetState;
        this.event = onEvent;
    }

    private TState from;
    private TEvent event;
    private TState to ;

    public TState getFrom() {
        return from;
    }

    public void setFrom(TState from) {
        this.from = from;
    }

    public TEvent getEvent() {
        return event;
    }

    public void setEvent(TEvent event) {
        this.event = event;
    }

    public TState getTo() {
        return to;
    }

    public void setTo(TState to) {
        this.to = to;
    }
}
