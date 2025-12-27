package com.net.controller;


import com.net.domain.ObjectFactory;
import com.net.domain.Student;
import com.net.dto.Post;
import com.net.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RESTFulHelloWorldController {

    private final HelloWorldService helloWorldService;

    @GetMapping("/sayRESTFulHelloWorld")
    public Student sayHelloWorld(
            @RequestParam(name = "name", required = false, defaultValue = "RESTFulHelloWorld") String name) {
        Student std = ObjectFactory.getInstance().createStudent();
        std.setId(1);
        std.setName(name);

        return std;
    }

    @GetMapping("/testJsonMapper")
    public Student testJsonMapper() {
        return helloWorldService.getStudent();
    }

    @GetMapping("/testFeign")
    public Post testFeign(@RequestParam(defaultValue = "1") Long id) {
        return helloWorldService.getPostById(id);
    }

    @GetMapping("/testAsync")
    public String testAsync() {
        helloWorldService.doSyncWork();
        return "Hello Async";
    }

    @GetMapping("/testFF4J")
    public String testFF4J() {
        helloWorldService.testFF4J();
        return "Hello FF4J";
    }

}
