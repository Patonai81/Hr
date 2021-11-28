package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrApplicationTestsWebClientIT {

    @Autowired
    WebTestClient webClient;

   // @Test
    public void testReplaceEmployeePositive()  {

        EmployeeDto employee = new EmployeeDto("Béla", 2000, LocalDateTime.of(2018, 4, 13, 8, 0, 0));

        webClient.put()
                .uri("/api/employees/" + employee.getId())
                .body(Mono.just(employee), EmployeeDto.class)
                .exchange()
                .expectStatus()
                .isOk();

    }

    @Test
    public void testSaveEmployeeInCompany() {
        CompanyDto company = getCompanies().get(0);
        EmployeeDto teszt = new EmployeeDto("Teszt T", 100000, LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40));

        webClient.post()
                .uri("/api/companies/" + company.getCompanyId()+"/employeeRelated")
                .body(Mono.just(teszt), EmployeeDto.class)
                .exchange()
                .expectStatus()
                .isOk().expectBody(EmployeeDto.class).consumeWith(item -> {
                    assertThat( item.getResponseBody().getEmployeeName().equals(teszt.getEmployeeName()));
                });


    }




        // helper methods ---------------------------------------------------------

    public List<EmployeeDto> getEmployees() {
        EntityExchangeResult<List<EmployeeDto>> result = webClient.get().uri(uriBuilder ->
                        uriBuilder
                                .path("/api/employee")
                                .queryParam("full", "false")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeDto.class)
                .returnResult();
        return result.getResponseBody();
    }

    public List<CompanyDto> getCompanies() {
        EntityExchangeResult<List<CompanyDto>> result = webClient.get().uri(uriBuilder ->
                        uriBuilder
                                .path("/api/companies")
                                .queryParam("full", "true")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CompanyDto.class)
                .returnResult();
        return result.getResponseBody();
    }


}
