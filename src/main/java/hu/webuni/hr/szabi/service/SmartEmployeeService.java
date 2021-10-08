package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.configuration.SalaryRelatedConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class SmartEmployeeService implements EmployeeService {

    @Autowired
    SalaryRelatedConfiguration salaryRelatedConfiguration;


    @Override
    public int getPayRaisePercentage(Employee employee) {

        long monthsBetween = ChronoUnit.MONTHS.between(employee.getStartWork(), LocalDateTime.now());
        if (monthsBetween < salaryRelatedConfiguration.getEmployeeService().get(0).getMaxmonth()) {
            return salaryRelatedConfiguration.getEmployeeService().get(0).getPercentage();
        } else if (monthsBetween < salaryRelatedConfiguration.getEmployeeService().get(1).getMaxmonth()) {
            return salaryRelatedConfiguration.getEmployeeService().get(0).getPercentage();
        } else if (monthsBetween < salaryRelatedConfiguration.getEmployeeService().get(2).getMaxmonth()) {
            return salaryRelatedConfiguration.getEmployeeService().get(2).getPercentage();
        }
        return salaryRelatedConfiguration.getMaxPercentage();
    }
}
