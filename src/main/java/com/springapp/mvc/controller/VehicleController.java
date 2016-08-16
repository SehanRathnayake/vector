package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Heshani Samarasekara on 8/16/2016.
 */
@Controller
@RequestMapping("/")
public class VehicleController {
    @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "vehicle";
    }
}
