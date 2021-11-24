package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.exception.CompanyNotFoundException;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.model.Position;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.repository.PositionRepository;
import hu.webuni.hr.szabi.service.CompanyService;
import hu.webuni.hr.szabi.service.EmployeeService;
import hu.webuni.hr.szabi.service.InitDbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;


@AutoConfigureTestDatabase
//Globális SQL futtatása
//@Sql(executionPhase= Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts="classpath:/test-sql/CompanyServiceIT.sql")
@SpringBootTest(properties = {"spring.config.name=myapp-test-h2", "myapp.trx.datasource.url=jdbc:h2:mem:trxServiceStatus"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CompanyServiceIT {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    InitDbService initDbService;

    private static final String testCompanyNameStr = "Company1";
    private final String positionName = "Segédmunkás";


    @BeforeEach
    public  void initDb(){
        initDbService.clearDb();
        initDbService.insertTestData();
    }

    @Test
    public void testSaveEmployeeInCompany() {

        Company company = companyRepository.findCompanyByNameWithEmployeeList(testCompanyNameStr).orElseThrow(() -> new CompanyNotFoundException("Test company does not exists anymore: "));
        Position position = positionRepository.findByPositionNameIgnoreCase(positionName).get();
        Employee teszt = new Employee("Teszt T", 100000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40), company, position);

        Employee saved = companyService.addEmployee(company.getCompanyId().intValue(), teszt, position.getPositionName());
        Employee backUp = employeeService.findByid(saved.getId().intValue());
        assertThat(saved).isEqualTo(backUp);

        Company company1 = companyRepository.findCompanyByNameWithEmployeeList(testCompanyNameStr).orElseThrow(() -> new CompanyNotFoundException("Test company does not exists anymore: "));
        assertThat(company1.getEmployeesList().size()).isGreaterThan(company.getEmployeesList().size());
    }

    @Test
    public void testRemoveEmployeeInCompany() {

        Company company = companyRepository.findCompanyByNameWithEmployeeList("Company2").orElseThrow(() -> new CompanyNotFoundException("Test company does not exists anymore: "));
        Position position = positionRepository.findByPositionNameIgnoreCase(positionName).get();
        Employee teszt = new Employee("Teszt T", 100000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40), company, position);

        Employee saved = companyService.addEmployee(company.getCompanyId().intValue(), teszt, position.getPositionName());
        companyService.removeEmployee(company.getCompanyId().intValue(), saved.getId().intValue());

        Company company1 = companyRepository.findCompanyByNameWithEmployeeList("Company2").orElseThrow(() -> new CompanyNotFoundException("Test company does not exists anymore: "));

        assertThat(company.getEmployeesList().size()).isEqualTo(company1.getEmployeesList().size());

        assertThat(company1.getEmployeesList())
                .hasSize(4)
                .extracting(Employee::getEmployeeName)
                .contains("Teszt Ubul", "Teszt Übül", "Teszt Töhötöm", "Teszt Tihamér");

    }

    @Test
    public void testReplaceEmployeesInCompany() {

        Company company = companyRepository.findCompanyByNameWithEmployeeList("Company2").orElseThrow(() -> new CompanyNotFoundException("Test company does not exists anymore: "));
        Position position = positionRepository.findByPositionNameIgnoreCase(positionName).get();

        List<Employee> employeeList= new ArrayList<>();
        employeeList.add(new Employee("Teszt Tihamér", 250000, LocalDateTime.of(2013, Month.FEBRUARY, 26, 19, 30, 40), company,position));
        employeeList.add(new Employee("Teszt Bübül",  700000, LocalDateTime.of(2011, Month.JANUARY, 29, 19, 30, 40), company,position));

        companyService.replaceEmployees(company.getCompanyId().intValue(),employeeList);

        Company companyRefreshed = companyRepository.findCompanyByNameWithEmployeeList("Company2").orElseThrow(() -> new CompanyNotFoundException("Test company does not exists anymore: "));

        assertThat(companyRefreshed.getEmployeesList()).hasSize(2).extracting(Employee::getEmployeeName).containsOnly("Teszt Tihamér","Teszt Bübül");


    }
}