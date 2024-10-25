package com.andt.ectool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("")
	public String toIndexPage() {
		return "index";
	}
}
