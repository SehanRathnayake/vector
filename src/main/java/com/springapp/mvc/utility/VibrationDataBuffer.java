package com.springapp.mvc.utility;

import com.springapp.mvc.dto.VibrationData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MaN
 *      on 8/10/2016.
 */
public class VibrationDataBuffer {
    static Map<Integer,Map<String,VibrationData>> buffer=null;

    public static Map<Integer,Map<String,VibrationData>> getBuffer() {
        return buffer;
    }

    public static void setBuffer(Map<Integer,Map<String,VibrationData>> buffer) {
        VibrationDataBuffer.buffer = buffer;
    }
    public static void insertData(Integer jobId,String key,VibrationData value){
        if(buffer!=null){
            buffer = new HashMap<Integer,Map<String,VibrationData>>();
        }
        Map<String,VibrationData> jobData = buffer.get(jobId);
        if(jobData==null){
            jobData = new HashMap<String, VibrationData>();
        }
        jobData.put(key,value);
        buffer.put(jobId,jobData);
    }
    public static VibrationData getValue(Integer jobId,String key){
        if(buffer!=null){
            return buffer.get(jobId).get(key);
        }else{
            return null;
        }
    }
}
