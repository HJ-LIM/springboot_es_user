package com.simple.juni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String root(){
		// return "redirect:/index.do";
		return "redirect:/swagger-ui.html";
	}

	@GetMapping("/index.do")
	public String index(){
		return "index";
	}
}
