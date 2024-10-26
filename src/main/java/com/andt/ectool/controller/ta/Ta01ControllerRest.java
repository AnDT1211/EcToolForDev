package com.andt.ectool.controller.ta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("ta01/api")
public class Ta01ControllerRest {

	@PostMapping("/convert")
	public Map<String, Object> convertA5ToMd(HttpServletRequest request) {
		String a5Input = request.getParameter("a5Input");
		List<String> ls = a5Input.lines().collect(Collectors.toList());
		Map<String, Object> output = new HashMap<>();
		output.put("header", ls.get(0).split("\\t"));
		ls.remove(0);
		output.put("body", ls.stream().map(x -> x.split("\\t")).toArray());
		return output;
	}
	
}
