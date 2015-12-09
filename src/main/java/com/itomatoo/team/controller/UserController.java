package com.itomatoo.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itomatoo.team.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String addtest() {
		System.out.println("ad");
		System.out.println(userService.getByUserName("admin"));
		System.out.println("aa");
		return "";
	}
}
