package com.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeatureController {

	@GetMapping("/preview")
	public String showFeaturePreview(
			@RequestParam(name = "user", required = false, defaultValue = "Guest") String user,
			Model model) {

		model.addAttribute("userName", user);
		return "feature";
	}

}
