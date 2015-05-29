package com.dorm18.geekren.website.security.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dorm18.geekren.website.security.User;

@RestController
public class RegistController {
	/**
	 * 
	 * <p>
	 * curl --data "email=rosicky&pwd=ppp&repwd=rrr"
	 * http://localhost:8080/website/api/regist
	 * </p>
	 * 
	 * @param email
	 * @param pwd
	 * @param repwd
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
		System.out.println(user);
		User ret = new User();
		ret.setEmail("rosicky1985@163.com");
		ret.setPassword("123");
		return ret;
	}
}
