package com.springapp.mvc.controller;


import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.utility.VibrationDataBuffer;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author MaN
 *      on 6/25/2016.
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    Integer jobId;
    @RequestMapping(value="/vibrationData/{espId}",method = RequestMethod.POST, headers = "content-type=application/json")
    public @ResponseBody
    void add(@PathVariable("espId") String espId,@RequestBody VibrationData data) {
        VibrationDataBuffer.insertData(jobId,espId,data);
        System.out.println("device id :"+espId+" xAxis :"+data.getxAxis()[0]);

      }

    @RequestMapping(value="/test",method = RequestMethod.POST, headers = "content-type=application/json")
    public @ResponseBody
    boolean test(@RequestBody String key) {
        VibrationData sampleData = new VibrationData();
        int[] xAxis = new int[100];
        int[] yAxis = new int[100];
        int[] zAxis = new int[100];
        for(int i=0;i<100;i++){
            xAxis[i]=500 + (int)(Math.random() * ((1000 - 500) + 1));
            yAxis[i]=500 + (int)(Math.random() * ((1000 - 500) + 1));
            zAxis[i]=500 + (int)(Math.random() * ((1000 - 500) + 1));
        }
        sampleData.setxAxis(xAxis);
        sampleData.setyAxis(yAxis);
        sampleData.setzAxis(zAxis);
        VibrationDataBuffer.insertData(jobId,key,sampleData);
        return true;
    }

    @RequestMapping(value="/jobId",method = RequestMethod.POST, headers = "content-type=application/json")
    public @ResponseBody
    void getJobId(@RequestBody String jobId) {
        this.jobId=Integer.parseInt(jobId);
    }
}
