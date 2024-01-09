package com.fnnek.bigcompany.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fnnek.bigcompany.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/list")
    public ResponseEntity populateCompaniesAndClients() {
        companyService.printAllCompanies();
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stream")
    public ResponseEntity populateOrders() {
        companyService.printAllCompaniesStream();
        
        return ResponseEntity.ok().build();
    }
    
}
