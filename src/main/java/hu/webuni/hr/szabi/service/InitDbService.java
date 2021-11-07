package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDbService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public void clearDb() {
        companyRepository.deleteAll();

    }


    public void insertTestData() {


        List<Employee> employeeList = new ArrayList<Employee>(6);

        Company company1 = new Company("Company1", "Company1Address", employeeList);

        employeeList.add(new Employee("Teszt Junior", "Junior", 100000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40), company1));
        employeeList.add(new Employee("Teszt Béla", "Expert", 250000, LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40), company1));
        employeeList.add(new Employee("Teszt Tamás", "Senior", 700000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40), company1));
        employeeList.add(new Employee("Teszt Aladár", "Vice President", 1350000, LocalDateTime.of(2000, Month.APRIL, 29, 19, 30, 40), company1));

        companyRepository.save(company1);

        Company company2 = new Company("Company2", "Company2Address", employeeList);

        employeeList.clear();
        employeeList.add(new Employee("Teszt Ubul", "Expert", 250000, LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40), company2));
        employeeList.add(new Employee("Teszt Übül", "Senior", 700000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40), company2));

        companyRepository.save(company2);


        Company company3 = new Company("Company3", "Company3Address", employeeList);

        employeeList.clear();
        employeeList.add(new Employee("Teszt Tihamér", "Expert", 250000, LocalDateTime.of(2013, Month.FEBRUARY, 26, 19, 30, 40), company3));
        employeeList.add(new Employee("Teszt Bübül", "Senior", 700000, LocalDateTime.of(2011, Month.JANUARY, 29, 19, 30, 40), company3));

        companyRepository.save(company3);


    }


}

