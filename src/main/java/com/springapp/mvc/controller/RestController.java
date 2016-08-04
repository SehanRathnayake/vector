package com.springapp.mvc.controller;


import com.springapp.mvc.dto.VibrationData;
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

        System.out.println("xAxis: ");
      }

    @RequestMapping(value="/test",method = RequestMethod.POST, headers = "content-type=application/json")
    public @ResponseBody
    void test() {
        System.out.println("esp id: ");
    }
}
