package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.configuration.ConfigObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("smart")
public class SmartEmployeeService extends AbstractEmployeeService {

    @Autowired
    ConfigObject configObject;

    @Override
    public int getPayRaisePercentage(Employee employee) {
        return configObject.getLimits().floorEntry(new Double(calculateMonths(employee))).getValue();
    }
}
