package hu.webuni.hr.szabi.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import hu.webuni.hr.szabi.model.*;
import hu.webuni.hr.szabi.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.repository.CompanyTypeRepository;
import hu.webuni.hr.szabi.repository.EmployeeRepository;

@Service
public class InitDbService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyTypeRepository companyTypeRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    PositionRepository positionRepository;


    @Transactional
    public void clearDb() {
        companyRepository.deleteAll();
        companyTypeRepository.deleteAll();
    }


    public void insertTestData() {

        CompanyTypeFromDB bt = companyTypeRepository.save(new CompanyTypeFromDB("BT"));
        CompanyTypeFromDB cd = companyTypeRepository.save(new CompanyTypeFromDB("CD"));
        CompanyTypeFromDB ed = companyTypeRepository.save(new CompanyTypeFromDB("ED"));
        CompanyTypeFromDB fd = companyTypeRepository.save(new CompanyTypeFromDB("FD"));
        CompanyTypeFromDB kkt = companyTypeRepository.save(new CompanyTypeFromDB("KKT"));

        Position position1 = positionRepository.save(new Position("Segédmunkás", EducationType.COURSE,10000L));
        Position position2 = positionRepository.save(new Position("Főiskolával végezhető munka", EducationType.COLLEGE,20000L));
        Position position3 = positionRepository.save(new Position("Középiskolával végezhető munka", EducationType.HIGH_SCHOOL,15000L));
        Position position4 = positionRepository.save(new Position("Egyetemi végzettséggel végezhető munka", EducationType.UNIVERSITY,30000L));


        List<Employee> employeeList = new ArrayList<Employee>(6);

        Company company1 = new Company("Company1", "Company1Address", employeeList, CompanyType.BT,bt);

        employeeList.add(new Employee("Teszt Junior", "Junior", 100000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40), company1,position1));
        employeeList.add(new Employee("Teszt Junior1", "Junior", 150000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40), company1,position1));
        employeeList.add(new Employee("Teszt Junior2", "Junior", 200000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40), company1,position3));

        employeeList.add(new Employee("Teszt Béla", "Expert", 250000, LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40), company1,position2));
        employeeList.add(new Employee("Teszt Tamás", "Senior", 700000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40), company1,position4));
        employeeList.add(new Employee("Teszt Aladár", "Vice President", 1350000, LocalDateTime.of(2000, Month.APRIL, 29, 19, 30, 40), company1,position4));

        companyService.save(company1);

        Company company2 = new Company("Company2", "Company2Address", employeeList, CompanyType.ZRT,fd);

        employeeList.clear();
        employeeList.add(new Employee("Teszt Ubul", "Expert", 250000, LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40), company2,position2));
        employeeList.add(new Employee("Teszt Übül", "Senior", 700000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40), company2,position4));
        employeeList.add(new Employee("Teszt Töhötöm", "Senior", 900000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40), company2,position4));
        employeeList.add(new Employee("Teszt Tihamér", "Senior", 1000000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40), company2,position4));


        companyService.save(company2);


        Company company3 = new Company("Company3", "Company3Address", employeeList, CompanyType.ZRT,kkt);

        employeeList.clear();
        employeeList.add(new Employee("Teszt Tihamér", "Expert", 250000, LocalDateTime.of(2013, Month.FEBRUARY, 26, 19, 30, 40), company3,position2));
        employeeList.add(new Employee("Teszt Bübül", "Senior", 700000, LocalDateTime.of(2011, Month.JANUARY, 29, 19, 30, 40), company3,position4));

        companyService.save(company3);


    }


}

