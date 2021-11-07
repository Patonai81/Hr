package hu.webuni.hr.szabi.repository;


import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CompanyTypeRepository extends CrudRepository<CompanyTypeFromDB, Long>, JpaSpecificationExecutor<CompanyTypeFromDB> {

}