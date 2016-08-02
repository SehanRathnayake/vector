package com.springapp.mvc.controller;

import com.springapp.mvc.service.UserService;
import com.springapp.mvc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller()
@RequestMapping("/")
public class DataController {

	@Autowired
	//@Qualifier("userService")
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
	userService.getName(5);
		return "home";
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String printHello(@RequestParam("value") String value) {

		return "home";
	}


	@RequestMapping(value = "vibrationdata/{id}/{value}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String saveValue(@PathVariable("id") long id, @PathVariable("value") long value){

		return "home";
	}

}