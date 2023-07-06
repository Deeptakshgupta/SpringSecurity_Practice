package com.L1_e1.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo")
	public String Demo()
	{
		var u= SecurityContextHolder.getContext().getAuthentication();
		u.getAuthorities().forEach(i-> System.out.println(i));
		return "Demo2"; 
	}
	
}
