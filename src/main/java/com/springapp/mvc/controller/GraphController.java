package com.springapp.mvc.controller;

import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MaN
 *         on 8/22/2016.
 */
@Controller()
@RequestMapping("/")
public class GraphController {
    @Autowired
    CacheService cacheService;

    @RequestMapping(value = "/graph", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    VibrationData printWelcome(@RequestBody String deviceId) {
        VibrationData data = cacheService.getVibrationData(Integer.parseInt(deviceId));
        return data;
    }
}
