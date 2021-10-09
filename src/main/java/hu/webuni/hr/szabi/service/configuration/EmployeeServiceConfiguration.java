package hu.webuni.hr.szabi.service.configuration;

import hu.webuni.hr.szabi.service.EmployeeService;
import hu.webuni.hr.szabi.service.SmartEmployeeService;
import hu.webuni.hr.szabi.service.SmartEmployeeYamlService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EmployeeServiceConfiguration {

    @Bean
    @Profile("smart")
    EmployeeService getEmployeeService() {
        return new SmartEmployeeService();
    }

    @Bean
    @Profile("smart-yaml")
    EmployeeService getEmployeeServiceYaml() {
        return new SmartEmployeeYamlService();
    }

}
