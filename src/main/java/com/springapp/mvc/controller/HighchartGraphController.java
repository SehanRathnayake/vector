package com.springapp.mvc.controller;

import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.service.CacheService;
import com.springapp.mvc.utility.VibrationDataBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Heshani Samarasekara on 8/10/2016.
 */

@Controller()
@RequestMapping("/")
public class HighchartGraphController {

    @Autowired
    CacheService cacheService;

    @RequestMapping(value = "/highchartGraph", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    VibrationData printWelcome(@RequestBody String deviceId) {
        // VibrationData data= VibrationDataBuffer.getValue(Integer.parseInt(jobId),"A001");
        VibrationData data = cacheService.getVibrationData(Integer.parseInt(deviceId));
        return data;
    }
}
