package com.springapp.mvc.service.impl;

import com.hazelcast.core.IMap;
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

    private IMap<Integer, VibrationData> vibrationDataMap;

    public IMap<Integer, Integer> getDeviceMap() {
        return deviceMap;
    }

    public void insertVibrationData(Integer deviceId, VibrationData value) {

        vibrationDataMap.put(deviceId, value, timeout, TimeUnit.MILLISECONDS);

    }

    public VibrationData getVibrationData(Integer deviceId) {
        return vibrationDataMap.get(deviceId);
    }

    public void setDeviceMap(IMap<Integer, Integer> deviceMap) {
        this.deviceMap = deviceMap;
    }

}
