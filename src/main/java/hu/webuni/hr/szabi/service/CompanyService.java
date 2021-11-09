package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.exception.CompanyCouldNotBeManipulatedException;
import hu.webuni.hr.szabi.exception.CompanyNotFoundException;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import hu.webuni.hr.szabi.repository.EmployeeRepository;
import hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(CompanyService.class);


    @PostConstruct
    public void init() {
    }


    public List<Company> findAll() {
        return this.findAll(null);
    }

    public List<Company> findAll(Pageable pageable) {
        if (pageable != null) {
            return companyRepository.findAll(pageable).getContent();
        } else {
            return companyRepository.findAll();
        }
    }


    public Company findById(Integer id) {
        Company company = companyRepository.findById(id.longValue()).orElseThrow(() -> new CompanyNotFoundException("Could not find company with a given ID" + id));
        logger.debug("findAll", company);
        return company;
    }


    @Transactional
    public Company save(Company company) {
        checkCompanyValidByAnnotation(company);
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

    public Company delete(Integer id) {
        Company company = findById(id);
        try {
            companyRepository.delete(company);
            return company;
        } finally {
            logger.debug("Company successfuly deleted" + company);
        }
    }

    @Transactional
    public Company modify(Integer id, Company company) {
        try {
            Company companyToBeModified = findById(id);
            companyToBeModified.setName(company.getName());
            companyToBeModified.setAddress(company.getAddress());

            return companyToBeModified;
        } finally {
            logger.debug("Company successfuly replaced" + company);
        }
    }

    @Transactional
    public void replaceEmployees(Integer companyId, List<Employee> employeeList) {
        try {
            Company companyToBeModified = findById(companyId);
            companyToBeModified.setEmployeesList(employeeList);

        } finally {
            logger.debug("Company successfuly replaced" + companyId);
        }
    }

    public List<Company> findCompaniesWithSalaryGtCondition(Integer salary) {
        logger.debug("Looking for companies salary greater than: " + salary);
        return companyRepository.findDistinctByEmployeesList_SalaryGreaterThanOrderByNameAsc(salary);
    }

    public List<Company> findCompaniesWithEmployeeNUmberGtCondition(Integer employeeNumber) {
        logger.debug("Looking for companies salary greater than: " + employeeNumber);
        return companyRepository.queryCompanyListWhereEmployeeNumberGt(employeeNumber.longValue());
    }

    public List<CompanyBYAVGSalaryResult> queryCompanyListAggregatedByAssignmentAndAvgSalaryOrderByAvgSalaryDesc() {
        return companyRepository.queryCompanyListAggregatedByAssignmentAndAvgSalaryOrderByAvgSalaryDesc();
    }

    private void checkCompanyValidByAnnotation(Company company) {
       /*
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Company>> violations = validator.validate(company);
      */
        Optional<CompanyTypeFromDB> companyTypeFromDB = companyTypeRepository.findByCompanyFormEqualsIgnoreCase(company.getCompanyTypeFromDB().getCompanyForm());
        if (companyTypeFromDB.isPresent()) {
            company.setCompanyTypeFromDB(companyTypeFromDB.get());
        } else {
            throw new CompanyCouldNotBeManipulatedException("Cannot create company with give company form: " + company.getCompanyTypeFromDB().getCompanyForm());
        }

    }

}
