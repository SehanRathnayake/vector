package com.springapp.mvc.dto;

import java.io.Serializable;

/**
 * Created by Heshani Samarasekara on 2/25/2017.
 */
public class ModelDto implements Serializable {
    private int modelId;
    private String modelName;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
