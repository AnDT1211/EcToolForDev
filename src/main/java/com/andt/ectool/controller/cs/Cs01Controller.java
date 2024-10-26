package com.andt.ectool.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cs01")
public class Cs01Controller {

	@GetMapping
	public String toTa01Page() {
		return "cs/cs01";
	}
}
