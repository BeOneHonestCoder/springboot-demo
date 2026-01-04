package com.net.controller;


import com.net.annotation.ActivityLog;
import com.net.domain.ObjectFactory;
import com.net.domain.Student;
import com.net.dto.Post;
import com.net.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RESTFulHelloWorldController {

    private final HelloWorldService helloWorldService;

    @GetMapping("/sayRESTFulHelloWorld")
    @PreAuthorize("hasRole('USER')")
    @ActivityLog("Test")
    public Student sayRESTFulHelloWorld(
            @RequestParam(name = "name", required = false, defaultValue = "RESTFulHelloWorld") String name) {
        log.info("REST Hello World!");
        Student std = ObjectFactory.getInstance().createStudent();
        std.setId(1);
        std.setName(name);

        return std;
    }

    @GetMapping("/sayHelloWorld")
    public String sayHelloWorld() {
        log.info("Hello World!");
        return "Hello World!";
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

}
