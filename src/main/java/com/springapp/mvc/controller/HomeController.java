package com.springapp.mvc.controller;

import com.springapp.mvc.dto.UserDetailsForAuthentication;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller()
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        return "home";
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void someControllerMethod(HttpServletRequest request, HttpSession session) {
        Boolean f = request.isUserInRole("Do a Test");
        UserDetailsForAuthentication user = (UserDetailsForAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        f = f;
    }

//	@RequestMapping(value = "/data", method = RequestMethod.GET)
//	public String printHello(@RequestParam("value") String value) {
//
//		return "home";
//	}
//
//
//	@RequestMapping(value = "vibrationdata/{id}/{value}", method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public String saveValue(@PathVariable("id") long id, @PathVariable("value") long value){
//
//		return "home";
//	}

}