package com.bugzai.service;


import com.bugzai.dto.travelplan.DriveTravalPlanDto;
import com.bugzai.dto.travelplan.DriveTravalPlanResultDto;

/**
 * @author bugzai
 *
 */
public interface TravelPlanService {
     /**
      * 驾驶规划
      * @param dto
      * @return
      */
     DriveTravalPlanResultDto DriveTravalPlan(DriveTravalPlanDto dto);

}
