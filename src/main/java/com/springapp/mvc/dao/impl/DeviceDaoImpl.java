package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.DeviceDao;
import com.springapp.mvc.model.Device;
import org.springframework.stereotype.Repository;

/**
 * Created by Sehan Rathnayake on 16/08/12.
 */

@Repository
public class DeviceDaoImpl extends BaseJpaDaoImpl<Device> implements DeviceDao {

    @Override
    public Device getDeviceById(Long id) {
        return getEntity(Device.class,id);
    }

    @Override
    public Device createDevice(Device device) {
        return saveEntity(device);
    }
}
