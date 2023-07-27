package com.ssL7.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Method levelAuthorization -> we need to enable these prepost before using that , which are to be declared where we are configuring the Security -> i.e. Config class
/** PrePost authorization **/

// Nethod level Auth can be applied on any SpringBean -> we just need to enable it in config class.
@RestController
@RequestMapping()
public class DemoController {

	@GetMapping("/demo")
	@PreAuthorize("hasAuthority('read')")// hasAuthority(),hasAnyAuthority(),hasRole(),hasAnyRole()
	public String demo() {
		return "demo";
		
	}
	
	@GetMapping("/demo2")
	@PreAuthorize("hasAuthority('write') or hasAuthority('read')")
//	@PreAuthorize("hasAuthority('write') or hasAuthority('read')") and specifies that both rights are required to call this endpoint
	public String demo2() {
		return "demo2";
		
	}
	
	@GetMapping("/demo3/{name}")
	@PreAuthorize(" #user == authentication.name ") //authentication from security context
	public String demo3(@PathVariable("name")String user)
	{
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		String username= auth.getName();
		System.out.println(username);
		return "Demo3";	
	}
}
