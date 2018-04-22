package com.net.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.net.domain.ObjectFactory;
import com.net.domain.Student;

@RestController
public class RESTFulHelloWorldController {

	@RequestMapping(value = "/sayRESTFulHelloWorld", method = RequestMethod.GET)
	public Student sayHelloWorld(
			@RequestParam(name = "name", required = false, defaultValue = "RESTFulHelloWorld") String name) {
		Student std = ObjectFactory.getInstance().createStudent();
		std.setId(1);
		std.setName(name);

		return std;
	}

}
