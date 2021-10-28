package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.EmployeeMapper;
import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.service.EmployeeService;
import hu.webuni.hr.szabi.validation.EmployeeDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@Component("employeeRest")
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    EmployeeDtoValidator employeeDtoValidator;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:63343")
    public List<EmployeeDto> getEmployeeList() {
        return employeeMapper.toEmployeeDtoList(employeeService.findAll());
    }


    @GetMapping("/{id}")
    public EmployeeDto getEmployeeList(@PathVariable Integer id) {
        return employeeMapper.toEmployeeDto(employeeService.findByid(id));
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody EmployeeDto employeeDto, BindingResult result) {

        //Oldschool validation
        employeeDtoValidator.validate(employeeDto,result);
        if (result.hasErrors()){
            throw new EmployeeCouldNotBeCreatedException(result.toString());
        }

        return ResponseEntity.ok(employeeMapper.toEmployeeDto(employeeService.save(employeeMapper.toEmployee(employeeDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable Integer id,@RequestBody @Valid EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeMapper.toEmployeeDto(employeeService.replace(id,employeeMapper.toEmployee(employeeDto))));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.remove(id);
    }

    @PostMapping("/raiseSalary")
    public int getPayRaisePercentage(@RequestBody  EmployeeDto e){
        return employeeService.getPayRaisePercentage(employeeMapper.toEmployee(e));
    }

}
