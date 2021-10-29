package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.mapper.CompanyMapper;
import hu.webuni.hr.szabi.mapper.EmployeeMapper;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@DependsOn("employeeRest")
@RequestMapping("/api/companies")
public class CompanRestController {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CompanyService companyService;

    //  @RequestMapping(value = {"", "/{id}"}, method = RequestMethod.GET)
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<CompanyDto> getCompany(@RequestParam(name = "full", required = false) String full) {
        if (null == full || "false".equalsIgnoreCase(full)) {
            return createFilterCompanyList(companyService.findAll(), true);
        }
        return createFilterCompanyList(companyService.findAll(), false);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(companyMapper.toCompanyDto(companyService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> addNewCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyMapper.toCompanyDto(companyService.save(companyMapper.toCompany(companyDto))));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyService.delete((int) id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyMapper.toCompanyDto(companyService.modify(id, companyMapper.toCompany(companyDto))));
    }

    @PostMapping("/{companyId}/employeeRelated")
    public EmployeeDto addNewEmployeeToCompany(@PathVariable Integer companyId, @RequestBody EmployeeDto employeeDto) {
        return employeeMapper.toEmployeeDtoList(companyService.addEmployee(companyId, employeeMapper.toEmployeeList(Arrays.asList(employeeDto)))).get(0);
    }

    @DeleteMapping("/{companyId}/employeeRelated/{employeeId}")
    public void deleteEmployeeFromCompany(@PathVariable Integer companyId, @PathVariable Integer employeeId) {
        companyService.removeEmployee(companyId, employeeId);
    }

    @PutMapping("/{companyId}/employeeRelated")
    public void replaceEmployees(@PathVariable Integer companyId, @RequestBody List<EmployeeDto> employeeDtoList) {
       companyService.replaceEmployees(companyId,employeeMapper.toEmployeeList(employeeDtoList));
    }

    private List<CompanyDto> createFilterCompanyList(List<Company> input, Boolean filterEmployee) {
        return companyService.findAll().stream().map((item) -> {
            CompanyDto internal = companyMapper.toCompanyDto(item);
            if (filterEmployee) {
                internal.setEmployeesList(null);
            }
            return internal;

        }).collect(Collectors.toList());
    }


}


