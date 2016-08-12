package com.springapp.mvc.dao;

import com.springapp.mvc.model.Device;

/**
 * Created by Sehan Rathnayake on 16/08/12.
 */
public interface DeviceDao {

    public Device getDeviceById(Long id);

    public Device createDevice(Device device);

}
