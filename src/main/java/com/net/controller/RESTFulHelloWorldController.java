package com.net.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.net.domain.ObjectFactory;
import com.net.domain.Student;
import com.net.mapper.JsonMapper;

@RestController
public class RESTFulHelloWorldController {

	private final JsonMapper mapper;

	public RESTFulHelloWorldController(JsonMapper mapper) {
		this.mapper = mapper;
	}

	@RequestMapping(value = "/sayRESTFulHelloWorld", method = RequestMethod.GET)
	public Student sayHelloWorld(
			@RequestParam(name = "name", required = false, defaultValue = "RESTFulHelloWorld") String name) {
		Student std = ObjectFactory.getInstance().createStudent();
		std.setId(1);
		std.setName(name);

		return std;
	}

	@RequestMapping(value = "/testJsonMapper", method = RequestMethod.GET)
	public Student testJsonMapper() throws JsonProcessingException {
		Student std = ObjectFactory.getInstance().createStudent();
		std.setId(1);
		std.setName("JsonMapper");
		
		String json = mapper.writeValueAsString(std);
		System.out.println(json);

		return std;
	}

}
