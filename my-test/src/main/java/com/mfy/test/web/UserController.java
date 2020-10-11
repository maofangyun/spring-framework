package com.mfy.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/queryUser")
    public @ResponseBody
    String queryUser() {
        return "jack";
    }

	@RequestMapping(value = "/queryUser",params ="id")
	public @ResponseBody
	String queryUser(@RequestParam String id) {
		return "jack-id";
	}
}
