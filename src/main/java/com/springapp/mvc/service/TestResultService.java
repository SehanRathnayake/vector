package com.springapp.mvc.service;

import com.springapp.mvc.dto.SuspensionTestResults;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
public interface TestResultService {
    public SuspensionTestResults getResults(long subJobID);

    public SuspensionTestResults getPastResults(long subJobId);
    public SuspensionTestResults getResults(String customer, String vehicle, String job, String wheel,long subjobId);

}
