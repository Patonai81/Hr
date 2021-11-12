package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.configuration.ConfigObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("default")
public class DefaultEmployeeService extends AbstractEmployeeService {

    @Autowired
    ConfigObject configObject;

    @Override
    public int getPayRaisePercentage(Employee employee) {
        return configObject.getNormal();
    }

    @Override
    public List<Employee> findEmployeeByAssignment(String assignment) {
        return employeeRepository.employeeFinderByPosition(assignment);
    }
}
