package com.net.controller;

import com.net.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SearchController {

	@GetMapping(value = "/search")
	public String search(@RequestParam(name = "parameter") String parameter, Model model) {
		model.addAllAttributes(buildResults());
		return "search";
	}

	private Map<String, Object> buildResults(){
		Map<String, Object> results = new HashMap<>();
		results.put("totalSize", 1);
		results.put("totalTime", 1);
		results.put("searchResults", Arrays.asList("Hello"));
		return results;

	}


}
