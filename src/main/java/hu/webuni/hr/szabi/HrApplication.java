package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.SalaryService;
import hu.webuni.hr.szabi.service.configuration.ConfigObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
public class HrApplication implements CommandLineRunner {

    @Autowired
    SalaryService salaryService;

    @Autowired
    ConfigObject configObject;

    List<Employee> employeeList = new ArrayList<Employee>(6);

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        setUpEmployees();
        employeeList.stream().forEach((f) -> {
            System.out.println("Before: " + f);
            salaryService.increaseEmployeeSalary(f);
            System.out.println("After: " + f);
        });



    }

    void setUpEmployees() {
        employeeList.add(new Employee(1l, "Teszt Junior", "Junior", 100000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40)));
        employeeList.add(new Employee(1l, "Teszt Béla", "Expert", 250000, LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40)));
        employeeList.add(new Employee(2l, "Teszt Tamás", "Senior", 700000, LocalDateTime.of(2015, Month.JANUARY, 29, 19, 30, 40)));
        employeeList.add(new Employee(3l, "Teszt Aladár", "Vice President", 1350000, LocalDateTime.of(2000, Month.APRIL, 29, 19, 30, 40)));

    }

}
