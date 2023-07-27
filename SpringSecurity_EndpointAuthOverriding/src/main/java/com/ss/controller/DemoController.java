package com.ss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo")
	public String mthd()
	{
		return "demo";
	}
	
}
