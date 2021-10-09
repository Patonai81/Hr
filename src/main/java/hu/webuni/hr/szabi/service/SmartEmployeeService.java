package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.configuration.SalaryRelatedPropertyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static hu.webuni.hr.szabi.service.configuration.SalaryRelatedPropertyConfiguration.MAXMONTH;
import static hu.webuni.hr.szabi.service.configuration.SalaryRelatedPropertyConfiguration.PERCENTAGE;


public class SmartEmployeeService implements EmployeeService {

    @Autowired
    SalaryRelatedPropertyConfiguration salaryRelatedConfiguration;

    @Override
    public int getPayRaisePercentage(Employee employee) {

        final long monthsBetween = calculateMonths(employee);

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
