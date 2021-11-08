package hu.webuni.hr.szabi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.szabi.model.CompanyTypeFromDB;

public interface CompanyTypeRepository extends JpaRepository<CompanyTypeFromDB, Long>, JpaSpecificationExecutor<CompanyTypeFromDB> {

}