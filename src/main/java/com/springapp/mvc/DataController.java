package com.springapp.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class DataController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String printHello(@RequestParam("value") String value) {

		return "hello";
	}


	@RequestMapping(value = "vibrationdata/{id}/{value}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String saveValue(@PathVariable("id") long id, @PathVariable("value") long value){

		return "hello";
	}

}