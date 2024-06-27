package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/controller/")
public class Controller {
	
	
	@GetMapping("/")
	public static String welcome() {
		return "welcome";
	}
}
