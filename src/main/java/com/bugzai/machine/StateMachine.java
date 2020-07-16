package com.bugzai.machine;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: StateMachine.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:41
 * @Version V1.0
 */
public class StateMachine<TEvent extends Comparable, TState extends Comparable> extends IStateMachine<TEvent, TState> {


    private TState _initState;

    public ActionMessage getMessage() {
        return message;
    }

    private ActionMessage message;

    @Override
    public IStateMachine init(ActionMessage actionMessage) {
        this.message=actionMessage;
        this.message.setStateMachine(this);
        return this;
    }

    @Override
   public boolean fire(TEvent event) {
        return FireInternal(event);
    }

    @Override
    public void reset() {
        CurrentStateId = _initState;
    }

    @Override
    public void start(TState initState) {
        _initState = initState;
        CurrentStateId = initState;
    }

    private boolean FireInternal(TEvent evt) {

        if (!TransitionRules.containsKey(CurrentStateId) || !TransitionRules.get(CurrentStateId).containsKey(evt)) {
            return false;
        }

        Transition transition = TransitionRules.get(CurrentStateId).get(evt);

        if (transition.getFrom().compareTo(CurrentStateId) != 0 || transition.getEvent().compareTo(evt) != 0) {
            return false;
        }

        CurrentStateId =(TState) transition.getTo();

        if (States.containsKey(transition.getTo()) && States.get(transition.getTo()).EnterAction != null) {
            States.get(transition.getTo()).EnterAction.Invoke(message);
        }
        return true;
    }



    public IEnterActionSyntax<TEvent, TState> In(TState state) {
        return new StateMachineBuilder<TEvent, TState>(this).In(state);
    }
}
