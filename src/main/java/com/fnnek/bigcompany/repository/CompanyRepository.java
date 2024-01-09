package com.fnnek.bigcompany.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fnnek.bigcompany.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{

    @Query("SELECT c from CompanyEntity c")
    List<CompanyEntity> getCompanies();

    @Query("SELECT c from CompanyEntity c")
    Stream<CompanyEntity> getCompaniesStream();
}
