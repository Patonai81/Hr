package hu.webuni.hr.szabi.repository;


import hu.webuni.hr.szabi.model.Company;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository2 extends CrudRepository<Company, Long>, JpaSpecificationExecutor<Company> {

}