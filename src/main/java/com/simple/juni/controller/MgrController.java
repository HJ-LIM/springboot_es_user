package com.simple.juni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mgr")
public class MgrController {

	@GetMapping("/")
	public String root(){
		return "redirect:/mgr/index.do";
	}

	@GetMapping("/index.do")
	public String index(){
		return "mgr.index";
	}
}
