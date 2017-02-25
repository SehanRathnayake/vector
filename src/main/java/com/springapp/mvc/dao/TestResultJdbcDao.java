package com.springapp.mvc.dao;

import com.springapp.mvc.dto.SuspensionTestResults;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
public interface TestResultJdbcDao {
    public void saveTestResults(SuspensionTestResults suspensionTestResults, long subJobId);

    public SuspensionTestResults getTestResults(long subJob);
}
