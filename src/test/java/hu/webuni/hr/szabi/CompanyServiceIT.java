package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.exception.CompanyNotFoundException;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.repository.CompanyRepository;
import hu.webuni.hr.szabi.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.annotation.DirtiesContext.ClassMode;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CompanyServiceIT {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    private static final String testCompanyNameStr = "Company2";


    @Test
    public void testSaveCompany() {
        Company company = companyRepository.findCompanyByName(testCompanyNameStr).orElseThrow(() ->new CompanyNotFoundException("Test company does not exists anymore: "));


    }
}
