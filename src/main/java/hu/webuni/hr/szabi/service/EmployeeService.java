package hu.webuni.hr.szabi.service;

import hu.webuni.hr.szabi.model.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    int getPayRaisePercentage(Employee employee);

    default long calculateMonths(Employee employee){
        return  ChronoUnit.MONTHS.between(employee.getStartWork(), LocalDateTime.now());

    }

    List<Employee> findAll();

    Employee findByid(Integer id);

    Employee save(Employee employee);

    void saveAll(List<Employee> employeeList);

    void removeAll(List<Employee> employeeList);

    Employee replace(Integer id, Employee employee);

    Employee remove(Integer id);

    List<Employee> findEmployeeByAssignment(String assignment);

    List<Employee> findEmployeesByStartingLetters(String nameFraction);

    List<Employee> findEmployeesBetweenStartDates(LocalDateTime startWorkStart, LocalDateTime startWorkEnd);


    void updateEmployeeSalary(String positionName, Integer minSalary, Optional<Integer> companyId);
}
