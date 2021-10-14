package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    EmployeeService employeeService;

    public void increaseEmployeeSalary(Employee employee) {
        employee.setSalary(employee.getSalary() + (employee.getSalary() / 100 * employeeService.getPayRaisePercentage(employee)));

    }
}
