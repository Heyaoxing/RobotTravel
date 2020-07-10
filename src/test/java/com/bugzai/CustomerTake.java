package com.bugzai;

import com.bugzai.machine.Action;
import com.bugzai.machine.ActionMessage;

/**
 * @Title: CustomerTake.java
 * @Package com.bugzai
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/9 19:12
 * @Version V1.0
 */
public class CustomerTake  {


    public void Invoke(ActionMessage message) {
        message.setName(message.getName()+" 用户取件");
        System.out.println("用户取件"+message.getName());
    }

    public void test(ActionMessage message){
        message.setName(message.getName()+" 这个是测试");
        System.out.println("这个是测试");
    }

    public void test1(ActionMessage message){
        message.setName(message.getName()+" 黑名单策略");
        message.getStateMachine().fire(MachineTest.TestEvent.Event3);
    }

    public void test2(ActionMessage message){
        message.setName(message.getName()+" ip防刷");
        message.getStateMachine().fire(MachineTest.TestEvent.Event5);
    }

    public void test3(ActionMessage message){
        message.setName(message.getName()+" 领取成功");
    }


    public void test5(ActionMessage message){
        message.setName(message.getName()+" 处理失败");
    }


}
