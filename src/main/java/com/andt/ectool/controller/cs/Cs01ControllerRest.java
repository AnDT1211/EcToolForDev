package com.andt.ectool.controller.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("cs01/api")
public class Cs01ControllerRest {

	@PostMapping("show")
	public Map<String, Object> show(HttpServletRequest request) {
		boolean isCsvContentHasHeader = Boolean.valueOf(request.getParameter("csv_csvContent_content_header"));
		
		String csvHeaderPhysical = request.getParameter("csv_physical_header");
		String csvContent = request.getParameter("csv_content");

		List<List<String>> csvContentOnly = convertMultiRowToList(csvContent);

		List<String> csvHeader = new ArrayList<>();
		if (isCsvContentHasHeader) {
			csvHeader = csvContentOnly.get(0);
			csvContentOnly.remove(0);
		}

		return Map.of("csvHeaderPhysical", convertRowToList(csvHeaderPhysical), "csvHeader", csvHeader, "csvContent",
				csvContentOnly);
	}

	@PostMapping("convert")
	public Map<String, Object> convert(@RequestBody List<List<String>> tableData) {
		String output = tableData.stream().filter(x -> x != null && !x.isEmpty()).map(x -> String.join(",", x)).collect(Collectors.joining("\n"));
		return Map.of("csvContent", output);
	}

	private List<List<String>> convertMultiRowToList(String rows) {
		return rows.lines().filter(x -> x != null && !x.isBlank()).map(this::convertRowToList).collect(Collectors.toList());
	}

	private List<String> convertRowToList(String row) {
		return Stream.of(row.split(",")).collect(Collectors.toList());
	}
}
