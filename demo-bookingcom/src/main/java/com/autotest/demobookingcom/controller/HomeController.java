package com.autotest.demobookingcom.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.autotest.demobookingcom.result.PostmanReportBean;
import com.autotest.demobookingcom.service.HomeService;



@RestController
@RequestMapping(value = "/booking")
public class HomeController {

    @Autowired
    HomeService homeService;

    @PostMapping("/test/home")
    public ResponseEntity<?> runTests() {
        try {
            PostmanReportBean postmanReportBean = homeService.runAllTests();
            return ResponseEntity.ok(postmanReportBean);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error writing results to CSV file.");
        }
        
        
    }
    
}
