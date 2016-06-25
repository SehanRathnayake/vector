package com.springapp.mvc;

import DTOs.VibrationData;
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
        System.out.println("esp id: "+espId);
    }

    @RequestMapping(value="/test",method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    void test(@RequestBody VibrationData data) {
        System.out.println("esp id: ");
    }
}
