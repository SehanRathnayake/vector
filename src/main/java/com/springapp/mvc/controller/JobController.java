/*
package com.springapp.mvc.controller;

import com.springapp.mvc.dto.CustVehicleTempDto;
import com.springapp.mvc.dto.DeviceWheelDto;
import com.springapp.mvc.dto.RawDataDto;
import com.springapp.mvc.dto.SuspensionTestResults;
import com.springapp.mvc.service.CacheService;
//import com.springapp.mvc.service.impl.TestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.RdbmsOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*/
/**
 * @author MaN
 *         on 2/20/2017.
 *//*

@Controller
@RequestMapping("/")
public class JobController {
    @Autowired
    CacheService cacheService;

    @RequestMapping(value = "/newJob", method = RequestMethod.GET)
    public String newJob(ModelMap model) {
        return "carconfig";
    }

    @RequestMapping(value = "/getChartData", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    SuspensionTestResults add(@RequestBody DeviceWheelDto[] devices) {
        SuspensionTestResults s = new TestResultServiceImpl().getResults(devices[0].getCustomerName(), devices[0].getVehicleName(), devices[0].getJobId() + "", devices[0].getWheelName());
        return s;
    }

    @RequestMapping(value = "/getWheelNames", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    List<String> add(@RequestBody String nameVehicle) {
        String[] split = nameVehicle.split("_");
        List<String> wheelNames = new ArrayList<String>();
        String path = "E:\\Vector Data\\";
        path += split[0] + "\\" + split[1] + "\\" + split[2] + "\\";
        File file = new File(path);
        String[] names = file.list();
        if (names != null) {
            for (String name : names) {
                if (new File(path + name).isDirectory()) {
                    wheelNames.add(name);
                }
            }
        }
        return wheelNames;
    }
    @RequestMapping(value = "/getCustomerNames", method = RequestMethod.POST, headers = "content-type=application/json")
    public
    @ResponseBody
    List<CustVehicleTempDto> customers(@RequestBody String nameVehicle) {
        List<String> wheelNames = new ArrayList<String>();
        String path = "E:\\Vector Data\\";
        File file = new File(path);
        String[] names = file.list();
        List<CustVehicleTempDto> custVehicleTempDtos = new ArrayList<CustVehicleTempDto>();
        if (names!= null) {
            for (String name : names) {
                CustVehicleTempDto dto = new CustVehicleTempDto();
                dto.setName(name);
                if (new File(path + name).isDirectory()) {
                    String newPath =path+name+"\\";
                    File newfile = new File(newPath);
                    String[] newNames = newfile.list();
                    List<String> vehicles = new ArrayList<String>();
                    if (newNames!= null) {
                        for (String x : newNames) {
                            if (new File(newPath + x).isDirectory()) {
                                vehicles.add(x);
                            }
                        }
                    }
                    dto.setVehicles(vehicles);
                }
                custVehicleTempDtos.add(dto);
            }
        }
        return custVehicleTempDtos;
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
        if (names != null) {
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
*/
