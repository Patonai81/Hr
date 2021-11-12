package hu.webuni.hr.szabi;

import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.web.MyErrorObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class HrApplicationTestsRestTemplateIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testaddNewEmployeePositive() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/employees";
        URI uri = new URI(baseUrl);


        EmployeeDto employee = new EmployeeDto("Béla",  2000, LocalDateTime.of(2018, 4, 13, 8, 0, 0));

        HttpHeaders headers = new HttpHeaders();
        //headers.set("X-COM-PERSIST", "true");
        HttpEntity<EmployeeDto> request = new HttpEntity<>(employee, headers);

        ResponseEntity<EmployeeDto> result = this.restTemplate.postForEntity(uri, request, EmployeeDto.class);

        //Verify request succeed
        assertThat(result.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    public void testaddNewEmployeeNegative() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/employees";
        URI uri = new URI(baseUrl);


        EmployeeDto employee = new EmployeeDto("Béla",  2000, LocalDateTime.of(2018, 4, 13, 8, 0, 0));

        HttpHeaders headers = new HttpHeaders();
        //headers.set("X-COM-PERSIST", "true");
        HttpEntity<EmployeeDto> request = new HttpEntity<>(employee, headers);

        ResponseEntity<MyErrorObject> result = this.restTemplate.postForEntity(uri, request, MyErrorObject.class);

        //Verify request succeed
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
        assertThat(result.getBody().getErrorCode()).isEqualTo(222);

    }


}

