package com.springapp.mvc.controller;


import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.service.CacheService;
import com.springapp.mvc.utility.VibrationDataBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Random;

/**
 * @author MaN
 *         on 6/25/2016.
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    @Autowired
    CacheService cacheService;
    String status;
    Integer jobId;

    @RequestMapping(value = "/vibrationData/{espId}", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    String add(@PathVariable("espId") Integer espId, @RequestBody VibrationData data) {
        //  VibrationDataBuffer.insertData(jobId,espId,data);
        cacheService.insertVibrationData(espId, data);
        System.out.println("device id :" + espId + " xAxis :" + data.getxAxis()[0]);
        setStatus("sd");
        return status;

    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    boolean test(@RequestBody String key) {
        VibrationData sampleData = new VibrationData();
        int[] xAxis = new int[100];
        int[] yAxis = new int[100];
        int[] zAxis = new int[100];
        for (int i = 0; i < 100; i++) {
            xAxis[i] = 500 + (int) (Math.random() * ((1000 - 500) + 1));
            yAxis[i] = 500 + (int) (Math.random() * ((1000 - 500) + 1));
            zAxis[i] = 500 + (int) (Math.random() * ((1000 - 500) + 1));
        }
        sampleData.setxAxis(xAxis);
        sampleData.setyAxis(yAxis);
        sampleData.setzAxis(zAxis);
        cacheService.insertVibrationData(Integer.parseInt(key), sampleData);
        return true;
    }

    @RequestMapping(value = "/jobId", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    void getJobId(@RequestBody String jobId) {
        this.jobId = Integer.parseInt(jobId);
    }

    @RequestMapping(value="/savefile",method=RequestMethod.POST)
    public ModelAndView upload(@RequestParam CommonsMultipartFile file, HttpSession session){
        String path=session.getServletContext().getRealPath("/");
        String filename=file.getOriginalFilename();

        System.out.println(path+" "+filename);
        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return new ModelAndView("upload-success","filename",path+"/"+filename);
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
