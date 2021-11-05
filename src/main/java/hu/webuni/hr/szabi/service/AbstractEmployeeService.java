package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.exception.EmployeeColdNotFoundException;
import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("none")
public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(AbstractEmployeeService.class);

    @PostConstruct
    public void init() {

    }

    @Override
    public List<Employee> findAll() {
     //   List<Employee> employeeList = employeeRepository.whatIs();
       List<Employee> employeeList = employeeRepository.findAll();
        logger.debug("findAll", employeeList);
        return employeeList;
    }

    @Override
    public Employee findByid(Integer id) {
        logger.debug("Incoming employee ID: " + id);
        return employeeRepository.findById(Integer.toUnsignedLong(id)).orElseThrow( () -> new EmployeeCouldNotBeCreatedException("Cannot find Employ"));
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        if (checkExist(employee.getId().intValue())){
            throw new EmployeeCouldNotBeCreatedException("Given ID is not unique: " + employee.getId().intValue());
        }
        employeeRepository.save(employee);
        logger.debug("Employee successfully created with id: " + employee.getId());
        return employee;
    }

    @Override
    @Transactional
    public Employee replace(Integer id, Employee employee) {
        if (!employeeRepository.existsById(Long.valueOf(id))){
            throw new EmployeeCouldNotBeCreatedException("Employee does not exists with this id, so cannot be updated");
        }
        employeeRepository.save(employee);
        logger.debug("Employee successfully replaced with id: " + id);
        return employee;
    }

    @Override
    public Employee remove(Integer id) {
        Employee employee= null;

        if (checkExist(id)) {
        employeeRepository.delete(employeeRepository.findById(id.longValue()).orElseThrow(() ->new EmployeeColdNotFoundException("Cannot find emploee with given id")));
        }else {
            throw new EmployeeCouldNotBeCreatedException("Cannot delete this user because it does not EXIST "+id);
        }
        logger.debug("Employee successfully replaced with id: " + id);
        return employee;
    }

    @Override
    public void saveAll(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }

    @Override
    public void removeAll(List<Employee> employeeList) {
        employeeRepository.deleteAll(employeeList);
    }

    @Override
    public List<Employee> findEmployeesByStartingLetters(String nameFraction) {
        return employeeRepository.findByEmployeeNameStartsWithIgnoreCaseOrderByEmployeeNameDesc(nameFraction);
    }

    @Override
    public List<Employee> findEmployeesBetweenStartDates(LocalDateTime startWorkStart, LocalDateTime startWorkEnd) {
        return employeeRepository.findEmployeesBetweenStartDates(startWorkStart,startWorkEnd);
    }

    private boolean checkExist(Integer id) {
        return  employeeRepository.countEmployeeByIdEquals(Long.valueOf(id)) > 0;
    }

}
