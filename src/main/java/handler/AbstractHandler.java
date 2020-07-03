package handler;

/**
 * @Title: AbstractHandler.java
 * @Package handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:00
 * @Version V1.0
 */
public abstract class AbstractHandler {

    protected AbstractHandler nextHandler;


    public HandlerMessage doNext(HandlerMessage message){
        message =  this.handler(message);
        if(message!=null&&message.getToNext()){
            return nextHandler.doNext(message);
        }
        return message;
    }

    protected abstract HandlerMessage handler(HandlerMessage message);

    public AbstractHandler setNextHandler(AbstractHandler nextHandler){
        this.nextHandler=nextHandler;
        return this.nextHandler;
    }
}
