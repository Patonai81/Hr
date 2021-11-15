package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.exception.CompanyCouldNotBeManipulatedException;
import hu.webuni.hr.szabi.exception.CompanyNotFoundException;
import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.CompanyTypeFromDB;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.model.Position;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import hu.webuni.hr.szabi.repository.EmployeeRepository;
import hu.webuni.hr.szabi.repository.PositionRepository;
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

    private final String segedmunkas = "Segédmunkás";
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    Logger logger = LoggerFactory.getLogger(CompanyService.class);


    @PostConstruct
    public void init() {
    }


    public List<Company> findAll(boolean isContent) {
        return this.findAll(null,isContent);
    }

    public List<Company> findAll(Pageable pageable, boolean isContent) {
        if (pageable != null) {
            return companyRepository.findCompaniesWithEmployees(pageable).getContent();
        } else {
            if (!isContent){
                return companyRepository.findAll();
            }else {
                return  companyRepository.findCompaniesWithEmployees();
            }
        }
    }


    public Company findById(Integer id) {
        Company company = companyRepository.findCompanyByCompanyId(id.longValue()).orElseThrow(() -> new CompanyNotFoundException("Could not find company with a given ID" + id));
        logger.debug("findById", company);
        return company;
    }


    @Transactional
    public Company save(Company company) {
        checkCompanyValidByAnnotation(company);
        Company saved = companyRepository.save(company);
        return saved;
    }


    public Employee addEmployee(Employee employee) {
        return this.addEmployee(employee.getCompanyToWorkFor().getCompanyId().intValue(),employee,employee.getPosition().getPositionName());
    }

    @Transactional
    public Employee addEmployee(Integer companyId, Employee employee, String position) {
        try {
            Optional<Position> positionFromRepo = positionRepository.findByPositionNameIgnoreCase(position);
            if (!positionFromRepo.isPresent()) {
                throw new CompanyCouldNotBeManipulatedException("Givan positiomn is not found: " + position);
            }
            //Megnézzük, h létezik e már,ha igen akkor nem adjuk megint hozzá
            if (employeeRepository.findEmployeebyName(employee.getEmployeeName()).isPresent()){
                logger.debug("Employee already exists: "+employee.getEmployeeName());
                throw new EmployeeCouldNotBeCreatedException("Employee is already exists with name: "+employee.getEmployeeName());
            }

            findById(companyId).addEmployee(employee, positionFromRepo.get());
        } finally {
            logger.debug("Employee " + employee + " successfully added to company id: " + companyId);
        }
        return employee;
    }

    @Transactional
    public Employee removeEmployee(Integer companyId, Integer employeeId) {
        try {
            Employee employeeServiceByid = employeeService.findByid(employeeId);
            findById(companyId).removeEmployee(employeeServiceByid);
            return employeeServiceByid;
        } finally {
            logger.debug("Employee " + employeeId + " successfully added to company id: " + companyId);
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
      //      companyToBeModified.setEmployeesList(employeeList);
            companyToBeModified.replaceEmployees(employeeList);

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
