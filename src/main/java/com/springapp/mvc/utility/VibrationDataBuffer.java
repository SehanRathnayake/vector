package com.springapp.mvc.utility;

import com.springapp.mvc.dto.VibrationData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MaN
 *      on 8/10/2016.
 */
public class VibrationDataBuffer {
    static Map<Integer,VibrationData> buffer=null;

    public static Map<Integer, VibrationData> getBuffer() {
        return buffer;
    }

    public static void setBuffer(Map<Integer, VibrationData> buffer) {
        VibrationDataBuffer.buffer = buffer;
    }
    public static void insertData(Integer key,VibrationData value){
        if(buffer!=null){
            buffer = new HashMap<Integer, VibrationData>();
        }
        buffer.put(key,value);
    }
    public static VibrationData getValue(Integer key){
        if(buffer!=null){
            return buffer.get(key);
        }else{
            return null;
        }
    }
}
