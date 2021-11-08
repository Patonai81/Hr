package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.dto.EmployeeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrApplicationTestsWebClientIT {

    @Autowired
    WebTestClient webClient;

    @Test
    public void testReplaceEmployeePositive()  {

        EmployeeDto employee = new EmployeeDto("Béla", "Alkesz", 2000, LocalDateTime.of(2018, 4, 13, 8, 0, 0));

        webClient.put()
                .uri("/api/employees/" + employee.getId())
                .body(Mono.just(employee), EmployeeDto.class)
                .exchange()
                .expectStatus()
                .isOk();

    //    System.out.println(result);

    }

}
