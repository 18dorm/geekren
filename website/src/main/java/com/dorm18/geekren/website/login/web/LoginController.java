package com.dorm18.geekren.website.login.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@RequestMapping("/login")
	public User login(String username, String pwd) {
		User ret = new User();
		ret.setEmail("rosicky1985@163.com");
		ret.setPassword("123");
		return ret;
	}
}
