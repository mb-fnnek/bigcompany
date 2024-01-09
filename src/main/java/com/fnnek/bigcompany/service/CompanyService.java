package com.fnnek.bigcompany.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.fnnek.bigcompany.entity.CompanyEntity;
import com.fnnek.bigcompany.repository.CompanyRepository;

import jakarta.persistence.EntityManager;

@Service
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    private final EntityManager entityManager;

    public CompanyService(CompanyRepository companyRepository, EntityManager entityManager) {
        this.companyRepository = companyRepository;
        this.entityManager = entityManager;
    }

    public void printAllCompaniesStream() {
        try (Stream<CompanyEntity> companies = companyRepository.getCompaniesStream()) {
            companies.forEach(this::printCompanyName);
        }
    }

    public void printAllCompanies() {
        companyRepository.getCompanies().forEach(this::printCompanyName);
    }

    private void printCompanyName(CompanyEntity companyEntity) {
        System.out.println(companyEntity.getName());
    }
}
