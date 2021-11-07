package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.exception.CompanyNotFoundException;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.repository.CompanyRepository2;
import hu.webuni.hr.szabi.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyRepository2 companyRepository2;

    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(CompanyService.class);


    @PostConstruct
    public void init() {
    }

    public List<Company> findAll() {
        List<Company> result = companyRepository.findAll();
        logger.debug("findAll", result);
        return result;
    }


    public Company findById(Integer id) {
        Company company = companyRepository.findById(id.longValue()).orElseThrow(() -> new CompanyNotFoundException("Could not find company with a given ID" + id));
        ;
        logger.debug("findAll", company);
        return company;
    }


    @Transactional
    public Company save(Company company) {
        Company saved = companyRepository.save(company);
        return saved;
    }

    @Transactional
    public Employee addEmployee(Integer companyId, Employee employee) {
        try {
            findById(companyId).addEmployee(employee);
            //Ha transactional akkor nem kötelező
            // employeeRepository.save(employee);
            return employee;
        } finally {
            logger.debug("Employee " + employee + " successfully added to company id: " + companyId);
        }
    }

    public Employee removeEmployee(Integer companyId, Integer employeeId) {
        try {
            Employee employeeServiceByid = employeeService.findByid(employeeId);
            findById(companyId).removeEmployee(employeeServiceByid);
            //Ha transactional akkor nem kötelező
            // employeeRepository.save(employee);
            return employeeServiceByid;
        } finally {
            logger.debug("Employee " + 1 + " successfully added to company id: " + companyId);
        }
    }

    public Company modify(Integer id, Company company) {
        return null;
    }

    public Company delete(Integer id) {
        return null;
    }

    public void replaceEmployees(Integer companyId, List<Employee> employeeList) {

    }

    public List<Company> findCompaniesWithSalaryGtCondition(Integer salary) {
        logger.debug("Looking for companies salary greater than: " + salary);
        return companyRepository.findDistinctByEmployeesList_SalaryGreaterThanOrderByNameAsc(salary);
    }

    public List<Company> findCompaniesWithEmployeeNUmberGtCondition(Integer employeeNumber) {
        logger.debug("Looking for companies salary greater than: " + employeeNumber);
        return companyRepository.queryCompanyListWhereEmployeeNumberGt(employeeNumber.longValue());
    }


}
