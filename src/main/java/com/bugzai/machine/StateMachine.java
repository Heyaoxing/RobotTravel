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
        TState oldState = CurrentStateId;

        if (!TransitionRules.containsKey(CurrentStateId) || !TransitionRules.get(CurrentStateId).containsKey(evt)) {
            return false;
        }

        Transition transition = TransitionRules.get(CurrentStateId).get(evt);

        if (transition.getFrom().compareTo(CurrentStateId) != 0 || transition.getEvent().compareTo(evt) != 0) {
            return false;
        }

        CurrentStateId =(TState) transition.getTo();

        if (States.containsKey(transition.getTo()) && States.get(transition.getTo()).EnterAction != null) {
            States.get(transition.getTo()).EnterAction.Invoke();
        }
        return true;
    }



    public IEnterActionSyntax<TEvent, TState> In(TState state) {
        return new StateMachineBuilder<TEvent, TState>(this).In(state);
    }
}
