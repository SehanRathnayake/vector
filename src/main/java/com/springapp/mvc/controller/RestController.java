package com.springapp.mvc.controller;


import com.springapp.mvc.dto.DeviceWheelDto;
import com.springapp.mvc.dto.ObdData;
import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.service.CacheService;
import com.springapp.mvc.utility.VibrationDataBuffer;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.Map;
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
    String status = "";
    Integer jobId;

    @RequestMapping(value = "/vibrationData/{espId}", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    String add(@PathVariable("espId") Integer espId, @RequestBody String data) {
        cacheService.insertDeviceData(espId, -1);
        System.out.println("device id :" + espId + " xAxis :" + data);
        Map<Integer,DeviceWheelDto> usedDevices=cacheService.getDeviceWheelMap();
        if (usedDevices.containsKey(espId) && usedDevices.get(espId).getStatus()==1) {
            return "sd";
        } else {
            return "wifi";
        }

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

    @RequestMapping(value = "/savefile/{espId}", method = RequestMethod.POST)
    public
    @ResponseBody
    String upload(@PathVariable("espId") Integer espId, @RequestPart("file") MultipartFile file, HttpSession session) {
        String path = "E:\\Vector Data\\";
        String filename = espId+"-"+file.getOriginalFilename();

        if(cacheService.getDeviceWheelMap().containsKey(espId)){
            DeviceWheelDto deviceWheelDto=cacheService.getDeviceWheelMap().get(espId);
            path+=deviceWheelDto.getCustomerName()+"\\"+deviceWheelDto.getVehicleName()+"\\"+deviceWheelDto.getJobId()+"\\"+deviceWheelDto.getWheelName()+"\\";
        }
        System.out.println(path + " " + filename);
        new File(path).mkdirs();
        try {
            byte barr[] = file.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path + "/" + filename));
            bout.write(barr);
            bout.flush();
            bout.close();
            DeviceWheelDto deviceWheelDto=cacheService.getDeviceWheelMap().get(espId);
            deviceWheelDto.setFileRecieved(true);
            deviceWheelDto.setStatus(-1);
            cacheService.insertDeviceWheel(espId,deviceWheelDto);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "wifi";
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @RequestMapping(value = "/obdData/{obdID}", method = RequestMethod.POST)
    public
    @ResponseBody
    String obdData(@PathVariable("obdID") Integer obdID, @RequestBody String indexData) {

        String value[] = indexData.split("N");
        for (String string : value) {
            String splitVlue[] = string.split(",");
            for (String string2 : splitVlue) {
                System.out.println(string2);
            }
        }
        return "Success";
    }

    @RequestMapping(value = "/obdRealTime/{obdID}", method = RequestMethod.POST)
    public
    @ResponseBody
    String jobStatus(@PathVariable("obdID") Integer obdID, @RequestBody ObdData obdData) {
        obdData.getRpm();
        String text = VibrationDataBuffer.getStatus();
        if (text.equals("RECORD")) {
            return "RECORD30000";
        } else {
            return text;
        }
    }

    @RequestMapping(value = "/jobStatus/{status}", method = RequestMethod.GET)
    public
    @ResponseBody
    String changeJobStatus(@PathVariable("status") String status) {
        String s = VibrationDataBuffer.getStatus();
        VibrationDataBuffer.setStatus(status);
        return status;
    }

    @RequestMapping(value = "/activeDevices", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer[] getActiveDevices() {
        Integer[] devices = new Integer[7];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : cacheService.getDeviceMap().entrySet()) {
            devices[i] = entry.getKey();
            i++;
        }
        //  Integer[] devices={1,2};
        return devices;
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean startActiveDevices(@RequestBody DeviceWheelDto[] devices) {
        for (DeviceWheelDto i : devices) {
            i.setStatus(1);
            cacheService.insertDeviceWheel(i.getDeviceId(),i);
        }
        return true;
    }

    @RequestMapping(value = "/testsave/{espId}", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@PathVariable("espId") Integer espId,
                             @RequestParam("file") MultipartFile file) {
        System.out.println("file acquired "+espId);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Create the file on server
                File serverFile = new File("E:\""+espId+"-datalog.txt");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();


            } catch (Exception e) {
                 e.getMessage();
            }
        } else {

        }
        return "wifi";
    }
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public @ResponseBody
    boolean getResults(@RequestBody DeviceWheelDto[] devices) {
        boolean results=true;
        for(DeviceWheelDto deviceWheelDto:devices){
            if(!cacheService.getDeviceWheelMap().get(deviceWheelDto.getDeviceId()).isFileRecieved()){
                results=false;
            }
        }
        return results;

    }

}
