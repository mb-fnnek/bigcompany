package com.fnnek.bigcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fnnek.bigcompany.populate.PopulateService;


@RestController
@RequestMapping("populate")
public class PopulateController {
    
    private final PopulateService populateService;

    public PopulateController(PopulateService populateService) {
        this.populateService = populateService;
    }

    @PostMapping("/companies-and-clients")
    public ResponseEntity populateCompaniesAndClients() {
        populateService.populateClientsAndCompanies();
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orders")
    public ResponseEntity populateOrders() {
        populateService.populateOrders();
        
        return ResponseEntity.ok().build();
    }
}
