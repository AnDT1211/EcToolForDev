package com.andt.ectool.controller.ta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ta01")
public class Ta01Controller {

	@GetMapping
	public String toTa01Page() {
		return "ta/ta01";
	}
}
