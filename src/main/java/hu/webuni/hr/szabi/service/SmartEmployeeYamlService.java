package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.configuration.SalaryRelatedYamlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartEmployeeYamlService implements EmployeeService {

    @Autowired
    SalaryRelatedYamlConfiguration salaryRelatedYamlConfiguration;

    @Override
    public int getPayRaisePercentage(Employee employee) {

        final long monthsBetween = calculateMonths(employee);
        try {
            SalaryRelatedYamlConfiguration.Limit limit = salaryRelatedYamlConfiguration.getLimits().entrySet().stream().filter(
                    (item) -> {
                        return Integer.valueOf(item.getValue().getMaxmonth()) > monthsBetween;
                    }
            ).findFirst().orElse(null).getValue();

            return Integer.valueOf(limit.getPercentage());

        } catch (NullPointerException e) {
            return salaryRelatedYamlConfiguration.getMaxPercentage();
        }


    }
}
