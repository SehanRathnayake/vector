package com.springapp.mvc.dto;

import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
public class VibrationData implements Serializable {
    private static final long serialVersionUID = 4044288636523558664L;

    private int[] xAxis;
    private int[] yAxis;
    private int[] zAxis;

    public int[] getxAxis() {
        return xAxis;
    }

    public void setxAxis(int[] xAxis) {
        this.xAxis = xAxis;
    }

    public int[] getyAxis() {
        return yAxis;
    }

    public void setyAxis(int[] yAxis) {
        this.yAxis = yAxis;
    }

    public int[] getzAxis() {
        return zAxis;
    }

    public void setzAxis(int[] zAxis) {
        this.zAxis = zAxis;
    }
}
