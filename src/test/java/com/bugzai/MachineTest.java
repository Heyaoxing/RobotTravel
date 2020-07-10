package com.bugzai;

import com.bugzai.machine.Action;
import com.bugzai.machine.ActionMessage;
import com.bugzai.machine.StateMachine;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
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
        Event4,
        Event5
    }

    enum TestState {
        State1,
        State2,
        State3,
        State4,
        State5,
    }

    @Test
    public void test() throws InterruptedException {
        CustomerTake customerTake = new CustomerTake();

        ActionMessage actionMessage = new ActionMessage();
        actionMessage.setName("领券");

        StateMachine machine = new StateMachine<TestEvent, TestState>(actionMessage);

        machine.In(TestState.State1).OnEnterState(customerTake::test).On(TestEvent.Event2).Goto(TestState.State2);

        machine.In(TestState.State2).OnEnterState(customerTake::test1)
                .On(TestEvent.Event3).Goto(TestState.State3)
                .On(TestEvent.Event5).Goto(TestState.State5);

        machine.In(TestState.State3).OnEnterState(customerTake::test2)
                .On(TestEvent.Event4).Goto(TestState.State4)
                .On(TestEvent.Event5).Goto(TestState.State5);

        machine.In(TestState.State4).OnEnterState(customerTake::test3).On(TestEvent.Event5).Goto(TestState.State5);

        machine.In(TestState.State5).OnEnterState(customerTake::test5);


        machine.start(TestState.State1);
        machine.fire(TestEvent.Event2);
        System.out.println(machine.getCurrentStateId());
        System.out.println(machine.getMessage().getName());
    }

    public void test06() {
        new Thread(System.out::println).start();
    }

}
