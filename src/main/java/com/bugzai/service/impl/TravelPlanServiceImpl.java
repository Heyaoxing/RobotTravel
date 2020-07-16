package com.bugzai.service.impl;

import com.bugzai.process.BaseProcess;
import com.bugzai.process.TravelProcess;
import com.bugzai.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bugzai
 */
@Service("travelPlanService")
public class TravelPlanServiceImpl implements TravelPlanService {


    @Resource(name = "travelProcess")
    private BaseProcess travelProcess;

    @Override
    public void walk() {
        travelProcess.process();
    }
}
