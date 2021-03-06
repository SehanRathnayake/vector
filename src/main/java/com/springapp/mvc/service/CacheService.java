package com.springapp.mvc.service;

import com.hazelcast.core.IMap;
import com.springapp.mvc.dto.DeviceWheelDto;
import com.springapp.mvc.dto.VibrationData;


/**
 * Created by Sehan Rathnayake on 16/08/17.
 */
public interface CacheService {

    public IMap<Integer, Integer> getDeviceMap();

    public void setDeviceMap(IMap<Integer, Integer> deviceMap);

    public void insertVibrationData(Integer deviceId, VibrationData value);

    public void insertDeviceData(Integer deviceId, Integer status);

    public VibrationData getVibrationData(Integer deviceId);

    public IMap<Integer, VibrationData> getVibrationDataMap();

    public void setVibrationDataMap(IMap<Integer, VibrationData> vibrationDataMap);

    public void setUsedDeviceMap(IMap<Integer, Integer> usedDeviceMap);

    public void insertUsedDevices(Integer deviceId, Integer status);

    public IMap<Integer, Integer> getUsedDeviceMap();

    public IMap<Integer, DeviceWheelDto> getDeviceWheelMap();

    public void setDeviceWheelMap(IMap<Integer,DeviceWheelDto> deviceWheelMap);

    public void insertDeviceWheel(Integer deviceId, DeviceWheelDto wheel);

}
