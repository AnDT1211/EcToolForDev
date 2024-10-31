package com.andt.ectool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("")
	public String toIndexPage() {
		return "index";
	}

	@GetMapping("cs01")
	public String toCs01Page() {
		return "cs/cs01";
	}

	@GetMapping("pc01")
	public String toPc01Page() {
		return "pc/pc01";
	}

	@GetMapping("pc02")
	public String toPc02Page() {
		return "pc/pc02";
	}
	
	@GetMapping("ta01")
	public String toTa01Page() {
		return "ta/ta01";
	}
}
