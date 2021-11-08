package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.service.InitDbService;
import hu.webuni.hr.szabi.service.SalaryService;
import hu.webuni.hr.szabi.service.configuration.ConfigObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    SalaryService salaryService;

    @Autowired
    ConfigObject configObject;

    @Autowired
    InitDbService initDbService;


    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
      initDbService.clearDb();
      initDbService.insertTestData();
    }




}
