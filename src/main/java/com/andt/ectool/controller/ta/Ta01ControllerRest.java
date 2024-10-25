package com.andt.ectool.controller.ta;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ta01/api")
public class Ta01ControllerRest {

	@PostMapping("/convert")
	public String convertA5ToMd(@RequestBody String a5Input) {
		List<String> ls = a5Input.lines().map(x -> x.replaceAll("\\t", "|")).collect(Collectors.toList());
		int c = (int)ls.get(0).chars().filter(x -> x == '|').count();
		String str = Stream.generate(() -> "-").limit(c + 1).collect(Collectors.joining("|"));
		ls.add(1, str);
		return ls.stream().collect(Collectors.joining("\n"));
	}
	
}
