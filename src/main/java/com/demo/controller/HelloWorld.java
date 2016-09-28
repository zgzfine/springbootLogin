package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

	@RequestMapping({"/", "/index"})
	public String index(){
		System.out.println("--------->index");
		return "login";
	}
}
