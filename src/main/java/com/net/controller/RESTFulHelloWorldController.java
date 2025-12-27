package com.net.controller;


import com.net.client.JsonPlaceholderClient;
import com.net.dto.Post;
import com.net.service.HelloWorldService;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.net.domain.ObjectFactory;
import com.net.domain.Student;
import com.net.mapper.JsonMapper;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RESTFulHelloWorldController {

	private final JsonMapper mapper;
	private final Tracer tracer;
	private final JsonPlaceholderClient jsonPlaceholderClient;
	private final HelloWorldService helloWorldService;

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
		//System.out.println(json);
		log.info(json);

		String traceId = MDC.get("traceId");
		log.info("traceId from MDC: {}", traceId);

		traceId = Optional.ofNullable(tracer.currentSpan())
				.map(span -> span.context().traceId())
				.orElse("none");
		log.info("traceId from Tracer: {}", traceId);

		return std;
	}

	@GetMapping("/testFeign")
	public Post testFeign(@RequestParam(defaultValue = "1") Long id) {
		return jsonPlaceholderClient.getPostById(id);
	}

	@GetMapping("/testAsync")
	public String testAsync() {
		helloWorldService.doSyncWork();
		return "Hello World";
	}

}
