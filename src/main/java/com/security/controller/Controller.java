package com.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/controller/")
public class Controller {
	
	
	@GetMapping("/")
	public static String welcome() {
		return "welcome";
	}

//	@PreAuthorize("hasRole('USER')")
	@GetMapping("user")
	public String userEndPoint(){
		return "hello User";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("admin")
	public String adminEndPoint(){
		return "hello Admin";
	}
}
