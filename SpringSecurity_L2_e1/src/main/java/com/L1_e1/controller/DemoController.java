package com.L1_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/Demo")
	public String Demo()
	{
		return "Demo2"; 
	}
	
}
