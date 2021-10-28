package hu.webuni.hr.szabi;


import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> toEmployeeDtoList (List<Employee> employeeList);

    List<Employee> toEmployeeList (List<EmployeeDto> employeeList);

   EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);


}
