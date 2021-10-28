package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Profile("none")
public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    EmployeeService employeeService;

    Map<Integer, Employee> employeeDtoMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(AbstractEmployeeService.class);

    @PostConstruct
    public void init() {
        employeeDtoMap.put(1, new Employee(1l, "Szabi", "Worker", 1000, LocalDateTime.of(2021, 4, 13, 8, 0, 0)));
        employeeDtoMap.put(2, new Employee(2l, "Béla", "Worker2", 2000, LocalDateTime.of(2018, 4, 13, 8, 0, 0)));

    }

    @Override
    public List<Employee> findAll() {
        logger.debug("findAll", employeeDtoMap.values());
        return new ArrayList(employeeDtoMap.values());
    }

    @Override
    public Employee findByid(Integer id) {
        logger.debug("Incoming employee ID: " + id);
        return employeeDtoMap.get(id);
    }

    @Override
    public Employee save(Employee employee) {
        if (checkExist(employee.getId().intValue())){
            throw new EmployeeCouldNotBeCreatedException("Given ID is not unique: " + employee.getId().intValue());
        }
        employeeDtoMap.put(employee.getId().intValue(), employee);
        logger.debug("Employee successfully created with id: " + employee.getId());
        return employee;
    }

    @Override
    public Employee replace(Integer id, Employee employee) {
        employeeDtoMap.put(employee.getId().intValue(), employee);
        logger.debug("Employee successfully replaced with id: " + employee.getId());
        return employee;
    }

    @Override
    public Employee remove(Integer id) {
        Employee employee= null;

        if (checkExist(id)) {
            employee = employeeDtoMap.remove(id);
        }else {
            throw new EmployeeCouldNotBeCreatedException("Cannot delete this user because it does not EXIST "+id);
        }
        logger.debug("Employee successfully replaced with id: " + id);
        return employee;
    }

    private boolean checkExist(Integer id) {
        return  (employeeDtoMap.containsKey(id));
    }

}
