package com.bugzai.process;

import com.bugzai.handler.AbstractHandler;
import com.bugzai.handler.WalkHandler;
import com.bugzai.handler.WeatherHandler;
import org.springframework.stereotype.Component;

/**
 * @Title: TravelProcess.java
 * @Package com.bugzai.process
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:24
 * @Version V1.0
 */
@Component("travelProcess")
public  class TravelProcess extends AbstractProcess {


    @Override
    protected AbstractHandler getHandler() {
        AbstractHandler walkHandler=new WalkHandler();
        return walkHandler;
    }
}
