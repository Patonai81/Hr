package hu.webuni.hr.szabi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CompanyTypeRepository extends JpaRepository<CompanyTypeFromDB, Long>, JpaSpecificationExecutor<CompanyTypeFromDB> {
    @NonNull
    CompanyTypeFromDB findByCompanyFormEqualsIgnoreCase(String companyForm);



}