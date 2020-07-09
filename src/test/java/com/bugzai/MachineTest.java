package com.bugzai;

import com.bugzai.machine.StateMachine;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Title: Machine.java
 * @Package com.bugzai
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 16:24
 * @Version V1.0
 */
public class MachineTest {
    enum TestEvent {
        Event1,
        Event2,
        Event3,
        Event4
    }

    enum TestState {
        State1,
        State2,
        State3,
        State4,
    }

    @Test
    public void test() throws InterruptedException {
        StateMachine machine = new StateMachine<TestEvent, TestState>();
        machine.In(TestState.State1).On(TestEvent.Event2).Goto(TestState.State2);

        machine.In(TestState.State2).OnEnterState(()->{
            machine.fire(TestEvent.Event3);
        }).On(TestEvent.Event3).Goto(TestState.State3);
        machine.start(TestState.State1);
        machine.fire(TestEvent.Event2);
        System.out.println(machine.getCurrentStateId());
    }
}
