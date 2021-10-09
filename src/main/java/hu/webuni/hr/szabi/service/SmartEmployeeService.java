package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.configuration.SalaryRelatedConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static hu.webuni.hr.szabi.service.configuration.SalaryRelatedConfiguration.MAXMONTH;
import static hu.webuni.hr.szabi.service.configuration.SalaryRelatedConfiguration.PERCENTAGE;


public class SmartEmployeeService implements EmployeeService {

    @Autowired
    SalaryRelatedConfiguration salaryRelatedConfiguration;

    @Override
    public int getPayRaisePercentage(Employee employee) {

        final long monthsBetween = ChronoUnit.MONTHS.between(employee.getStartWork(), LocalDateTime.now());

        try {
            Map<String, String> configItem = salaryRelatedConfiguration.getMapOfList().entrySet().stream().filter(
                    (item) -> {
                        return Integer.valueOf(item.getValue().get(MAXMONTH)) > monthsBetween;
                    }
            ).findFirst().orElse(null).getValue();
            return Integer.valueOf(configItem.get(PERCENTAGE));

        } catch (NullPointerException e) {
            return salaryRelatedConfiguration.getMaxPercentage();
        }

    }
}
