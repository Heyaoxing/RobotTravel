package com.bugzai.container;

import com.bugzai.common.enums.BaseEvent;
import com.bugzai.common.enums.BaseState;
import com.bugzai.components.ReadyComponent;
import com.bugzai.machine.ActionMessage;
import com.bugzai.machine.IStateMachine;
import com.bugzai.machine.StateMachine;
import org.springframework.beans.factory.InitializingBean;
import com.bugzai.common.enums.BaseEvent.*;
import com.bugzai.common.enums.BaseState.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: ReadyMatchine.java
 * @Package com.bugzai.container
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/13 15:10
 * @Version V1.0
 */
@Component("readyMatchine")
public class ReadyMatchine implements  InitializingBean {

    @Resource(name="readyComponent")
    private ReadyComponent readyComponent;


    private StateMachine machine;

    public IStateMachine getMachine(ActionMessage actionMessage) {
        machine.start(ReadyStateEnum.INTI);
        return machine.init(actionMessage);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        machine = new StateMachine<ReadyActionEnum, ReadyStateEnum>();

        machine.In(ReadyStateEnum.INTI).On(ReadyActionEnum.START).Goto(ReadyStateEnum.START)
                .On(ReadyActionEnum.STOP).Goto(ReadyStateEnum.STOP);

        machine.In(ReadyStateEnum.START).OnEnterState(readyComponent.getReadyAction().get(ReadyActionEnum.START))
                .On(ReadyActionEnum.RUN).Goto(ReadyStateEnum.RUN)
                .On(ReadyActionEnum.STOP).Goto(ReadyStateEnum.STOP);

        machine.In(ReadyStateEnum.RUN).OnEnterState(readyComponent.getReadyAction().get(ReadyActionEnum.RUN))
                .On(ReadyActionEnum.STOP).Goto(ReadyStateEnum.STOP);

        machine.In(ReadyStateEnum.STOP).OnEnterState(readyComponent.getReadyAction().get(ReadyActionEnum.STOP));
    }

}
