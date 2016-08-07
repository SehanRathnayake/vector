package com.springapp.mvc.controller;

import com.springapp.mvc.dto.UserDetailsForAuthentication;
import com.springapp.mvc.service.UserService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


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
    @RequestMapping("/sparkline")
    public ModelAndView crunchifySparklineTest() {
        return new ModelAndView("sparkline");
    }

    @RequestMapping(value = "/sparklinetest", method = RequestMethod.GET)
    public @ResponseBody
    String constructJSONArray() throws JSONException {

        JSONObject one = new JSONObject();
        JSONObject two = new JSONObject();
        JSONObject three = new JSONObject();

        JSONArray result = new JSONArray();
        Random r = new Random();
        int[] r1 = { r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100),r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100) };
        int[] r2 = { r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100),r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100) };
        int[] r3 = { r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100),r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100) };

        one.put("one", r1);
        two.put("two", r2);
        three.put("three", r3);

        result.put(one);
        result.put(two);
        result.put(three);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sparkData", result);
        System.out.println("Sendig this data to view (sparkline.jsp): " + jsonObj.toString());

        return jsonObj.toString();
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