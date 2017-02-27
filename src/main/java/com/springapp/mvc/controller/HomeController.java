package com.springapp.mvc.controller;

import com.springapp.mvc.dto.JobDto;
import com.springapp.mvc.dto.SuspensionTestResults;
import com.springapp.mvc.dto.UserDetailsForAuthentication;
import com.springapp.mvc.model.Job;
import com.springapp.mvc.service.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


@Controller()
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    CacheService cacheService;

    @Autowired
    TestResultService testResultService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        //List<JobDto> g = jobService.getJobs(1);
      //  testResultService.getResults(1);
        return "home";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(ModelMap model) {

        UserDetailsForAuthentication user = (UserDetailsForAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uid = userService.getUser(user.getUsername()).getUserId();
        Long jobid = jobService.createNewJob(new Long(3), uid);
        //  Long sub = jobService.createSubJob(jobid, "FRONT LEFT");
        SuspensionTestResults suspensionTestResults = testResultService.getResults(154);

        //   List<Job> j=jobService.getJobs(3);

        //   HashMap<String, Long> getSubJobs=jobService.getSubJobs(j.get(0).getJobId());
        SuspensionTestResults t = testResultService.getPastResults(154);
        return "home";

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void someControllerMethod(HttpServletRequest request, HttpSession session) {
        // Boolean f = request.isUserInRole("Do a Test");
        UserDetailsForAuthentication user = (UserDetailsForAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.getUser(user.getUsername()).getUserId();
    }


}