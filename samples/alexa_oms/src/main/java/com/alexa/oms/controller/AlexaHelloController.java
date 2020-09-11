package com.alexa.oms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlexaHelloController {
	
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)	
	public String sayHello()
	{
		return "Hello Cardinal ! Hello Edge Park Supplies";
	}

}
