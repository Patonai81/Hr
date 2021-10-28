package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.model.Employee;
import hu.webuni.hr.szabi.service.EmployeeService;
import hu.webuni.hr.szabi.validation.EmployeeDtoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
@Component("employeeRest")
@RequestMapping("/api/employees")
public class EmployeeRestController {

    Map<Integer, EmployeeDto> employeeDtoMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeDtoValidator employeeDtoValidator;

    @PostConstruct
    public void init() {
        employeeDtoMap.put(1, new EmployeeDto(1l, "Szabi", "Worker", 1000, LocalDateTime.of(2021, 4, 13, 8, 0, 0)));
        employeeDtoMap.put(2, new EmployeeDto(2l, "Béla", "Worker2", 2000, LocalDateTime.of(2018, 4, 13, 8, 0, 0)));

    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:63343")
    public List<EmployeeDto> getEmployeeList() {
        return employeeDtoMap.values().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeList(@PathVariable Integer id) {
        return employeeDtoMap.get(id);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody EmployeeDto employeeDto, BindingResult result) {

        employeeDtoValidator.validate(employeeDto,result);
        if (result.hasErrors()){
            logger.error("Add new employee has error!");
           throw new EmployeeCouldNotBeCreatedException(result.toString());
        }

        if (employeeDtoMap.containsKey(employeeDto.getId().intValue())) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            employeeDtoMap.put(employeeDto.getId().intValue(), employeeDto);
        }
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable Integer id,@RequestBody @Valid EmployeeDto employeeDto) {

        if (!employeeDtoMap.containsKey(id)) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            employeeDtoMap.put(employeeDto.getId().intValue(), employeeDto);
        }
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeDtoMap.remove(id);
    }

    @PostMapping("/raiseSalary")
    public int getPayRaisePercentage(@RequestBody  Employee e){
        return employeeService.getPayRaisePercentage(e);
    }

}
