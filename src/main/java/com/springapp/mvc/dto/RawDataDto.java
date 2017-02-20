package com.springapp.mvc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author MaN
 *         on 2/20/2017.
 */
public class RawDataDto implements Serializable {
    private List<Integer> vibration;
    private List<Integer> time;

    public List<Integer> getVibration() {
        return vibration;
    }

    public void setVibration(List<Integer> vibration) {
        this.vibration = vibration;
    }

    public List<Integer> getTime() {
        return time;
    }

    public void setTime(List<Integer> time) {
        this.time = time;
    }
}
