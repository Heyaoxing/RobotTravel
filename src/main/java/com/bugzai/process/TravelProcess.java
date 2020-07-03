package com.bugzai.process;

import handler.AbstractHandler;
import handler.HandlerMessage;
import org.springframework.beans.factory.InitializingBean;

import java.util.Objects;

/**
 * @Title: TravelProcess.java
 * @Package com.bugzai.process
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:24
 * @Version V1.0
 */
public abstract class TravelProcess implements BaseProcess, InitializingBean {

    private AbstractHandler doHandler;

    @Override
    public void process(){
        HandlerMessage message=new HandlerMessage();
        if(Objects.nonNull(doHandler)){
            message = doHandler.doNext(message);
        }

        if(Objects.isNull(message)||!message.getToNext()){
            return;
        }


    }




    protected abstract AbstractHandler getHandler();

    @Override
    public void afterPropertiesSet(){
        doHandler=this.getHandler();
    }

}
