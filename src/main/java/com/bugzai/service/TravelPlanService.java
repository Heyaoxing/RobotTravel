package com.bugzai.service;


import com.bugzai.dto.travelplan.DriveTravalPlanDto;
import com.bugzai.dto.travelplan.DriveTravalPlanResultDto;
import com.bugzai.dto.travelplan.LocationDto;
import com.bugzai.dto.travelplan.LocationResultDto;

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

     /**
      * 地点检索
      * @param dto
      * @return
      */
     LocationResultDto searchLocation(LocationDto dto);
}
