package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
public class DefaultEmployeeService implements EmployeeService {

    @Override
    public int getPayRaisePercentage(Employee employee) {
        return 5;
    }
}
