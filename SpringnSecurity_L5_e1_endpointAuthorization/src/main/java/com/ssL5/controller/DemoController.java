package com.ssL5.controller;

import org.springframework.web.bind.annotation.GetMapping;

/*
 These are the Endpoints 
 */
public class DemoController {

	/** Demo Endpoint**/
	@GetMapping("/demo")
	public String demo()
	{
		return "Demo:";
	}
	
}
