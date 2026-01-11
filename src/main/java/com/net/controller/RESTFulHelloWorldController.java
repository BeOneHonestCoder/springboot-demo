package com.net.controller;


import com.net.annotation.ActivityLog;
import com.net.domain.Student;
import com.net.dto.Post;
import com.net.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class RESTFulHelloWorldController {

    private final HelloWorldService helloWorldService;

    @GetMapping("/greeting")
    @PreAuthorize("hasRole('USER')")
    @ActivityLog("Greeting")
    public ResponseEntity<Student> sayHello(
            @RequestParam(name = "name", required = false, defaultValue = "Guest") String name) {
        log.info("Request to say hello to: {}", name);
        Student student = helloWorldService.getStudent();
        return ResponseEntity.ok(student);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> simpleHello() {
        log.info("Hello World!");
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = helloWorldService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        helloWorldService.triggerAsyncWork();
        return ResponseEntity.ok("Demo done!");
    }

}
