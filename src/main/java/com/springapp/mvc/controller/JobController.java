package com.springapp.mvc.controller;

import com.springapp.mvc.dto.DeviceWheelDto;
import com.springapp.mvc.dto.RawDataDto;
import com.springapp.mvc.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.RdbmsOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MaN
 *         on 2/20/2017.
 */
@Controller
@RequestMapping("/")
public class JobController {
    @Autowired
    CacheService cacheService;

    @RequestMapping(value = "/newJob", method = RequestMethod.GET)
    public String newJob(ModelMap model) {
        return "carconfig";
    }

    @RequestMapping(value = "/getRawData", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    List<RawDataDto> add(@RequestBody DeviceWheelDto[] devices) {
        List<RawDataDto> dataList = new ArrayList<RawDataDto>();
        for (DeviceWheelDto deviceWheelDto : devices) {
            RawDataDto r = new RawDataDto();
            String path = "E:\\Vector Data\\";
            path += deviceWheelDto.getCustomerName() + "\\" + deviceWheelDto.getVehicleName() + "\\"+deviceWheelDto.getJobId()+"\\" + deviceWheelDto.getWheelName() + "\\";
            try {
                BufferedReader br = new BufferedReader(new FileReader(path + "\\" + deviceWheelDto.getDeviceId() + "-datalog.txt"));
                String line = br.readLine();
                List<Integer> vibrations = new ArrayList<Integer>();
                List<Integer> time = new ArrayList<Integer>();
                while (line != null) {
                    String[] split = line.split(" ");
                    vibrations.add(Integer.parseInt(split[0]));
                    time.add(Integer.parseInt(split[3]));
                    line = br.readLine();
                }
                r.setVibration(vibrations);
                r.setTime(time);
                dataList.add(r);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    @RequestMapping(value = "/getWheelNames", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    List<String> add(@RequestBody String nameVehicle) {
        String[] split = nameVehicle.split("_");
        List<String> wheelNames = new ArrayList<String>();
        String path = "E:\\Vector Data\\";
        path += split[0] + "\\" + split[1] + "\\"+split[2]+"\\";
        File file = new File(path);
        String[] names = file.list();
        if (names!= null) {
            for (String name : names) {
                if (new File(path + name).isDirectory()) {
                    wheelNames.add(name);
                }
            }
        }
        return wheelNames;
    }

    @RequestMapping(value = "/getJobId", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    Integer getJobId(@RequestBody String nameVehicle) {
        String[] split = nameVehicle.split("_");
        List<Integer> jobs = new ArrayList<Integer>();
        String path = "E:\\Vector Data\\";
        path += split[0] + "\\" + split[1] + "\\";
        File file = new File(path);
        String[] names = file.list();
        if (names!= null) {
            for (String name : names) {
                if (new File(path + name).isDirectory()) {
                    try {
                        jobs.add(Integer.parseInt(name));
                    } catch (Exception e) {

                    }
                }
            }
        }
        return jobs.size() + 1;
    }

}
