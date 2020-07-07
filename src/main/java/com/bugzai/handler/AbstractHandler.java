package com.bugzai.handler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title: AbstractHandler.java
 * @Package handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:00
 * @Version V1.0
 */
@Slf4j
public abstract class AbstractHandler {

    protected AbstractHandler nextHandler;


    public HandlerMessage doNext(HandlerMessage message) {
        message = this.wrapHandler(message);
        if (message != null && message.getToNext()) {
            return nextHandler.doNext(message);
        }
        return message;
    }

    private HandlerMessage wrapHandler(HandlerMessage message) {
        try {
            message = this.handler(message);
            log.info(new Gson().toJson(message));
        } catch (Throwable e) {
            message.setToNext(false);
            message.setPreviousMessage(e.getMessage());
            log.error("handlerMessage : ", e);
        }
        return message;
    }

    protected abstract HandlerMessage handler(HandlerMessage message) throws InterruptedException;

    public AbstractHandler setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    /**
     * 停止向下调用
     *
     * @param message
     */
    protected void stopNext(HandlerMessage message) {
        message.setToNext(false);
    }
}
