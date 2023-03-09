package com.iv1201.recapp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller for testing access to controller.
 */
@RestController
@RequestMapping("/api/v1/testEndpoint")
public class TestController {

    /**
     * Test endpoint for testing.
     * @return String with response to test.
     */
    @GetMapping("/anotherTestEndpoint")
    public ResponseEntity<String> anotherTest(){
        System.out.println("test");
        return ResponseEntity.ok("Another Test");
    }
}
