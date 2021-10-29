package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.dto.mytype.CompanyMap;
import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class CompanyService {

    @Autowired
    EmployeeService employeeService;

    private CompanyMap<Integer, Company> companies = new CompanyMap();
    Logger logger = LoggerFactory.getLogger(CompanyService.class);


    @PostConstruct
    public void init() {
         companies.put(1, new Company(1, "Name", "Address", employeeService.findAll()));
    }


    public List<Company> findAll(){
        logger.debug("findAll", companies.values());
        return new ArrayList(companies.values());
    }

    public Company findById(Integer id){
        Company company = companies.get(id);
        return company;
    }

    public Company save(Company company){
        //csak aggregálom az employeekat, nincs oda vissza referencia
        employeeService.saveAll(company.getEmployeesList());
        return companies.put(company.getCompanyId(),company,false);
    }

    public List<Employee> addEmployee(Integer companyId,List<Employee> employee){
        employeeService.saveAll(employee);
        Company company= findById(companyId);
        company.getEmployeesList().addAll(employee);
        return employee;
    }

    public Employee removeEmployee(Integer companyId,Integer employeeId){
        employeeService.remove(employeeId);
           Optional<Employee> employee= findById(companyId).getEmployeesList().stream().filter(employeeItem -> employeeItem.getId().intValue() == employeeId.intValue()).findFirst();
            if (employee.isPresent()){
                findById(companyId).getEmployeesList().remove(employee.get());
            }else{
                throw new EmployeeCouldNotBeCreatedException("Could not delete employee: "+employeeId+" from company: "+companyId);
            }
        return employee.get();
    }

    public Company modify(Integer id, Company company){
        //csak aggregálom az employeekat, nincs oda vissza referencia
        employeeService.saveAll(company.getEmployeesList());
        return companies.put(id,company,true);
    }

    public Company delete (Integer id){
        Company company = companies.remove(id);
        employeeService.removeAll(company.getEmployeesList());
        return company;
    }

    public void replaceEmployees(Integer companyId,List<Employee> employeeList){
        //csak az új idkat menti rá
        employeeService.saveAll(employeeList);
        Company company = findById(companyId);
        company.getEmployeesList().clear();
        company.getEmployeesList().addAll(employeeList);
    }

}
