package com.bugzai.machine;

import java.util.HashMap;

/**
 * @Title: StateMachineBuilder.java
 * @Package com.bugzai.machine
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 15:14
 * @Version V1.0
 */
public class StateMachineBuilder<TEvent  extends Comparable,TState  extends Comparable> implements IEnterActionSyntax<TEvent,TState>,IOnSyntax<TEvent,TState>,IGotoSyntax<TEvent,TState>,IInSyntax<TEvent,TState>  {


    private Action _enterAction;
    private TEvent _onEvent;
    private TState _fromState;
    private IStateMachine<TEvent, TState> _machine;

    public StateMachineBuilder(IStateMachine<TEvent,TState> machine)
    {
        _machine = machine;
    }

    @Override
    public IEnterActionSyntax<TEvent, TState> In(TState state) {
        if (!_machine.States.containsKey(state)){
           _machine.States.put(state,new State<TEvent, TState>(state));
        }
        _fromState = state;
        return this;
    }

    @Override
    public IOnSyntax<TEvent, TState> OnEnterState(Action enterAction) {
        _enterAction = enterAction;
        _machine.States.get(_fromState).EnterAction = _enterAction;
        return this;
    }

    @Override
    public IOnSyntax<TEvent, TState> Goto(TState targetState) {
        if (!_machine.TransitionRules.containsKey(_fromState))
        {
            _machine.TransitionRules.put(_fromState, new HashMap<>());
        }

        _machine.TransitionRules.get(_fromState).put(_onEvent,new Transition<TEvent,TState>(_fromState,_onEvent,targetState));
        return this;
    }


    @Override
    public IGotoSyntax<TEvent, TState> On(TEvent evt) {
        _onEvent = evt;
        return this;
    }
}
