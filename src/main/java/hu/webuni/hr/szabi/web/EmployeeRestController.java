package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.exception.EmployeeCouldNotBeCreatedException;
import hu.webuni.hr.szabi.mapper.EmployeeMapper;
import hu.webuni.hr.szabi.service.EmployeeService;
import hu.webuni.hr.szabi.validation.EmployeeDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController()
@Component("employeeRest")
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    EmployeeDtoValidator employeeDtoValidator;

    @Autowired
    EmployeeMapper employeeMapperecske;

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:63343")
    public List<EmployeeDto> getEmployeeList() {
        return employeeMapperecske.toEmployeeDtoList(employeeService.findAll());
    }


    @GetMapping("/{id}")
    public EmployeeDto getEmployeeList(@PathVariable Integer id) {
        return employeeMapperecske.toEmployeeDto(employeeService.findByid(id));
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody EmployeeDto employeeDto, BindingResult result) {

        //Oldschool validation
        employeeDtoValidator.validate(employeeDto,result);
        if (result.hasErrors()){
            throw new EmployeeCouldNotBeCreatedException(result.toString());
        }

        return ResponseEntity.ok(employeeMapperecske.toEmployeeDto(employeeService.save(employeeMapperecske.toEmployee(employeeDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable Integer id,@RequestBody @Valid EmployeeDto employeeDto) {
        System.out.println("Modify employee called");
        return ResponseEntity.ok(employeeMapperecske.toEmployeeDto(employeeService.replace(id, employeeMapperecske.toEmployee(employeeDto))));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.remove(id);
    }

    @PostMapping("/raiseSalary")
    public int getPayRaisePercentage(@RequestBody  EmployeeDto e){
        return employeeService.getPayRaisePercentage(employeeMapperecske.toEmployee(e));
    }

    @GetMapping("/employeeAssignment")
    public List<EmployeeDto> getEmployeeListByAssignment(@RequestParam String assignment) {
        return employeeMapperecske.toEmployeeDtoList(employeeService.findEmployeeByAssignment(assignment));
    }

    @GetMapping("/employeeStartsWith")
    public List<EmployeeDto> findEmployeesByStartingLetters(@RequestParam String name) {
        return employeeMapperecske.toEmployeeDtoList(employeeService.findEmployeesByStartingLetters(name));
    }

    @GetMapping("/employeeBetweenDates")
    public List<EmployeeDto> findEmployeesByStartingLetters(LocalDateTime from, LocalDateTime to) {
        return employeeMapperecske.toEmployeeDtoList(employeeService. findEmployeesBetweenStartDates(from, to));
    }

    @PostMapping("/raiseSalaryForPosition")
    public void raiseSalary(@RequestParam("positionName") String positionName, @RequestParam("salaryMin") Integer salaryMin){
        employeeService.updateEmployeeSalary(positionName,salaryMin);
    }




}
