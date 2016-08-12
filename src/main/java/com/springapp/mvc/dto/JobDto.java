package com.springapp.mvc.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public class JobDto implements Serializable {

    private HashMap<Long,Long> deviceSetup;

    public HashMap<Long,Long> getDeviceSetup() {
        return deviceSetup;
    }

    public void setDeviceSetup(HashMap<Long,Long> deviceSetup) {
        this.deviceSetup = deviceSetup;
    }
}
