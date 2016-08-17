package com.springapp.mvc.service.impl;

import com.hazelcast.core.IMap;
import com.springapp.mvc.service.CacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created by Sehan Rathnayake on 16/08/17.
 */

@Service
public class CacheServiceImpl implements CacheService {


    private IMap<Integer, Integer> deviceMap;

    public IMap<Integer, Integer> getDeviceMap() {
        deviceMap.put(673, 6, 10000, TimeUnit.MILLISECONDS);
        deviceMap.put(894, 6, 100000, TimeUnit.MILLISECONDS);
        return deviceMap;
    }

    public void setDeviceMap(IMap<Integer, Integer> deviceMap) {
        this.deviceMap = deviceMap;
    }

}
