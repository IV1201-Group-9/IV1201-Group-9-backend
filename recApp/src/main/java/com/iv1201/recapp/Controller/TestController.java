package com.iv1201.recapp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api/v1/testEndpoint")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/anotherTestEndpoint")
    public ResponseEntity<String> anotherTest(){
        System.out.println("test");
        return ResponseEntity.ok("Another Test");
    }
}
