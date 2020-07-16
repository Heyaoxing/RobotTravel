package com.bugzai.handler;

import com.bugzai.common.enums.BaseEvent;
import com.bugzai.common.enums.BaseState;
import com.bugzai.container.ReadyMatchine;
import com.bugzai.machine.ActionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: ReadyHandler.java
 * @Package com.bugzai.handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/7 10:57
 * @Version V1.0
 */
@Slf4j
@Component("readyHandler")
public class ReadyHandler extends AbstractHandler {

    @Resource(name="readyMatchine")
    private ReadyMatchine readyMatchine;

    @Override
    protected ActionMessage handler(ActionMessage message) {
        readyMatchine.getMachine(message).fire(BaseEvent.ReadyActionEnum.START);
        return message;
    }
}
