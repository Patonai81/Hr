package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.mapper.CompanyMapper;
import hu.webuni.hr.szabi.mapper.EmployeeMapper;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult;
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

      //Megoldás 1
        /*
        if (null == full || "false".equalsIgnoreCase(full)) {
            return createFilterCompanyList(companyService.findAll(), true);
        }
        return createFilterCompanyList(companyService.findAll(), false);

*/
        if (null == full || "false".equalsIgnoreCase(full)) {
            return companyMapper.toCompanyDtoListWithoutEmployee(companyService.findAll());
        } else {
            return companyMapper.toCompanyDtoList(companyService.findAll());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(companyMapper.toCompanyDto(companyService.findById(id)));
    }

    @GetMapping("/{salary}/salaryGt")
    public ResponseEntity<List<CompanyDto>> getCompanyOfSalaryGT(@PathVariable Integer salary) {
        return ResponseEntity.ok(companyMapper.toCompanyDtoList(companyService.findCompaniesWithSalaryGtCondition(salary)));
    }

    @GetMapping("/{employeeNum}/employeeNumGt")
    public ResponseEntity<List<CompanyDto>> getCompanyOfEmployeeNumGT(@PathVariable Integer employeeNum) {
        return ResponseEntity.ok(companyMapper.toCompanyDtoList(companyService.findCompaniesWithEmployeeNUmberGtCondition(employeeNum)));
    }

    @GetMapping("/getCompanyByAVGSalary")
    public List <CompanyBYAVGSalaryResult> getCompanyByAVGSalary(){
        return companyService.queryCompanyListAggregatedByAssignmentAndAvgSalaryOrderByAvgSalaryDesc();
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
        return employeeMapper.toEmployeeDto(companyService.addEmployee(companyId, employeeMapper.toEmployee(employeeDto)));
    }

    @DeleteMapping("/{companyId}/employeeRelated/{employeeId}")
    public void deleteEmployeeFromCompany(@PathVariable Integer companyId, @PathVariable Integer employeeId) {
        companyService.removeEmployee(companyId, employeeId);
    }

    @PutMapping("/{companyId}/employeeRelated")
    public void replaceEmployees(@PathVariable Integer companyId, @RequestBody List<EmployeeDto> employeeDtoList) {
       companyService.replaceEmployees(companyId,employeeMapper.toEmployeeList(employeeDtoList));
    }




        //Megoldás 1. a filterelésre
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


