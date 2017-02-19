package com.springapp.mvc.service.impl;

import com.hazelcast.core.IMap;
import com.springapp.mvc.dto.DeviceWheelDto;
import com.springapp.mvc.dto.VibrationData;
import com.springapp.mvc.service.CacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created by Sehan Rathnayake on 16/08/17.
 */

@Service
public class CacheServiceImpl implements CacheService {

    private int timeout = 10000;

    private IMap<Integer, Integer> deviceMap;

    private IMap<Integer, Integer> usedDeviceMap;

    private IMap<Integer, VibrationData> vibrationDataMap;

    public IMap<Integer,DeviceWheelDto> deviceWheelMap;

    public IMap<Integer, Integer> getDeviceMap() {
        return deviceMap;
    }

    public void insertVibrationData(Integer deviceId, VibrationData value) {

        vibrationDataMap.put(deviceId, value, timeout, TimeUnit.MILLISECONDS);

    }

    public void insertDeviceData(Integer deviceId, Integer status){
        deviceMap.put(deviceId,status,timeout,TimeUnit.MILLISECONDS);
    }

    public IMap<Integer, VibrationData> getVibrationDataMap() {
        return vibrationDataMap;
    }

    public void setVibrationDataMap(IMap<Integer, VibrationData> vibrationDataMap) {
        this.vibrationDataMap = vibrationDataMap;
    }

    public VibrationData getVibrationData(Integer deviceId) {
        return vibrationDataMap.get(deviceId);
    }

    public void setDeviceMap(IMap<Integer, Integer> deviceMap) {
        this.deviceMap = deviceMap;
    }

    public void setUsedDeviceMap(IMap<Integer, Integer> usedDeviceMap) {
        this.usedDeviceMap = usedDeviceMap;
    }

    public void insertUsedDevices(Integer deviceId, Integer status){
        usedDeviceMap.put(deviceId,status,65000,TimeUnit.MILLISECONDS);
    }

    public IMap<Integer, Integer> getUsedDeviceMap() {
        return usedDeviceMap;
    }

    public IMap<Integer, DeviceWheelDto> getDeviceWheelMap() {
        return deviceWheelMap;
    }

    public void setDeviceWheelMap(IMap<Integer, DeviceWheelDto> deviceWheelMap) {
        this.deviceWheelMap = deviceWheelMap;
    }
    public void insertDeviceWheel(Integer deviceId, DeviceWheelDto wheel){
        deviceWheelMap.put(deviceId,wheel);
    }

    public DeviceWheelDto getDeviceWheelData(Integer id){
        return deviceWheelMap.get(id);
    }
}
