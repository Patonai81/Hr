package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public interface EmployeeService {

    int getPayRaisePercentage(Employee employee);

    default long calculateMonths(Employee employee){
        return  ChronoUnit.MONTHS.between(employee.getStartWork(), LocalDateTime.now());

    }
}
