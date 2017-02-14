package com.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping(value = "/say", method = RequestMethod.GET)
	public ModelAndView sayHelloWorld() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("helloworld");

		return mv;
	}
	
//	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
//    @ResponseBody
//	public String sayHelloWorld() {
//
//		return "hello, world";
//	}
	



}
