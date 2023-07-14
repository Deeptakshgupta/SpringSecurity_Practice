package com.ssL5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 These are the Endpoints 
 */
@RestController
public class DemoController {

	/** Demo Endpoint**/
	@GetMapping("/demo")
	public String demo()
	{
		return "Demo:";
	}
	
}
