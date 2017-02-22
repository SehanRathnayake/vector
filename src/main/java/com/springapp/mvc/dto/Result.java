package com.springapp.mvc.dto;

/**
 * Created by Sehan Rathnayake on 17/02/22.
 */
public class Result {
    int id;
    SuspensionTestResults suspensionTestResults;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SuspensionTestResults getSuspensionTestResults() {
        return suspensionTestResults;
    }

    public void setSuspensionTestResults(SuspensionTestResults suspensionTestResults) {
        this.suspensionTestResults = suspensionTestResults;
    }
}
