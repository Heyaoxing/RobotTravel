package com.bugzai.process;

import com.bugzai.handler.AbstractHandler;
import com.bugzai.handler.HandlerMessage;
import com.bugzai.machine.ActionMessage;
import org.springframework.beans.factory.InitializingBean;

import java.util.Objects;

/**
 * @Title: AbstractProcess.java
 * @Package com.bugzai.process
 * @Description: (用一句话描述该文件做什么)
 * @Author: 002954
 * @Date: 2020/7/6 16:03
 * @Version V1.0
 */
public abstract class AbstractProcess implements BaseProcess, InitializingBean {
    private AbstractHandler doHandler;

    @Override
    public void process() {
        ActionMessage message = this.wrapMessage();
        if (Objects.nonNull(message) && Objects.nonNull(doHandler)) {
            message = doHandler.doNext(message);
        }

        if (Objects.isNull(message) || !message.getToNext()) {
            return;
        }
    }

    protected abstract HandlerMessage wrapMessage();


    protected abstract AbstractHandler getHandler();

    @Override
    public void afterPropertiesSet() {
        doHandler = this.getHandler();
    }

}
