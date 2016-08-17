package com.springapp.mvc.service;

import com.hazelcast.core.IMap;


/**
 * Created by Sehan Rathnayake on 16/08/17.
 */
public interface CacheService {

    public IMap<Integer, Integer> getDeviceMap();

    public void setDeviceMap(IMap<Integer, Integer> deviceMap);

}
