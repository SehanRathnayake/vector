package com.springapp.mvc.controller;


import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.utility.VibrationDataBuffer;
import org.springframework.web.bind.annotation.*;

/**
 * @author MaN
 *      on 6/25/2016.
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @RequestMapping(value="/vibrationData/{espId}",method = RequestMethod.POST, headers = "content-type=application/json")
    public @ResponseBody
    void add(@PathVariable("espId") String espId,@RequestBody VibrationData data) {
        VibrationDataBuffer.insertData(Integer.parseInt(espId),data);
        System.out.println("device id :"+espId+" xAxis :"+data.getxAxis()[0]);
      }

    @RequestMapping(value="/test",method = RequestMethod.POST, headers = "content-type=application/json")
    public @ResponseBody
    void test() {
        System.out.println("esp id: ");
    }
}
