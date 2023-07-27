package com.ssL7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/demo4/{name}")
//	@PreAuthorize("(#user == authentication.name) or (hasAuthority('read) or hasAuthority('write'))")
	//authentication from security context
	/** NotAdviced to use these conditions directly here, as it may cause debugging problems later on
 instead make a different separate class and provide a method with all the conditions and then call that
  here in the argument**/
	public String demo4(@PathVariable("name")String user)
	{
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		String username= auth.getName();
		System.out.println(username);
		return "Demo4";	
	}
	
	@GetMapping("/demo5/{name}")
	@PreAuthorize("@ConditionEvaluator.condition()")//authentication from security context
	public String demo5(@PathVariable("name")String user)
	{
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		String username= auth.getName();
		System.out.println(username);
		return "Demo5";	
	}
	
	// @preFilter -> works with Array or Collection
	@GetMapping("/demo6")
	@PreFilter("filterObject.contains('a')") // no necessarily to specify the filter target, if we have only one list
/** filterObject w.r.t the parameter type here we are using String**/	
	public String demo6(@RequestBody List<String> values) {
		values.forEach(System.out::println);
		return "Demo6";
	}
	
//	@POstFilter => we filter returned type must be either Collection/Array
	@GetMapping("/demo7")
	@PostFilter("filterObject.contains('a')")
	public List<String> demo7()
	{
		List<String> list = new ArrayList<>();
		list.add("abcd");
		list.add("bcde");
		list.add("defg");
		list.add("afgh");
//		List.of(...);// List.of always creates an immutable Collection 
		return list;
	}
}
