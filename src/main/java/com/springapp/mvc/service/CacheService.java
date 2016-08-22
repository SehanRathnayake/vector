package com.springapp.mvc.service;

import com.hazelcast.core.IMap;
import com.springapp.mvc.dto.VibrationData;


/**
 * Created by Sehan Rathnayake on 16/08/17.
 */
public interface CacheService {

    public IMap<Integer, Integer> getDeviceMap();

    public void setDeviceMap(IMap<Integer, Integer> deviceMap);

    public void insertVibrationData(Integer deviceId, VibrationData value);

    public VibrationData getVibrationData(Integer deviceId);

    public IMap<Integer, VibrationData> getVibrationDataMap();

    public void setVibrationDataMap(IMap<Integer, VibrationData> vibrationDataMap);

}
