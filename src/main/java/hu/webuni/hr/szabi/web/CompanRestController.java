package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.mapper.CompanyMapper;
import hu.webuni.hr.szabi.mapper.EmployeeMapper;
import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.model.EducationType;
import hu.webuni.hr.szabi.model.Position;
import hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult;
import hu.webuni.hr.szabi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public List<CompanyDto> getCompany(@RequestParam(name = "full", required = false) Boolean full) {

        //Megoldás 1
        /*
        if (null == full || "false".equalsIgnoreCase(full)) {
            return createFilterCompanyList(companyService.findAll(), true);
        }
        return createFilterCompanyList(companyService.findAll(), false);

*/
        if (!full) {
            return companyMapper.toCompanyDtoListWithoutEmployee(companyService.findAll(false));
        } else {
            return companyMapper.toCompanyDtoList(companyService.findAll(true));
        }

    }

    @RequestMapping(value = {"/pageTest"}, method = RequestMethod.GET)
    public List<CompanyDto> getCompany(@RequestParam(required = false) String companyNameFragment,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "3") int size) {

        List<Company> companies;
        Pageable paging = PageRequest.of(page, size, Sort.by("name"));

        if (companyNameFragment == null)
            companies = companyService.findAll(paging,true);
        else
            //TODO implement
            return null;

        return companyMapper.toCompanyDtoList(companies);
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
    public List<CompanyBYAVGSalaryResult> getCompanyByAVGSalary() {
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
    public EmployeeDto addNewEmployeeToCompany(@PathVariable Integer companyId, @RequestBody EmployeeDto employeeDto, @RequestParam(defaultValue = "segédmunkás") String position ) {
        return employeeMapper.toEmployeeDto(companyService.addEmployee(companyId, employeeMapper.toEmployee(employeeDto),position));
    }

    @DeleteMapping("/{companyId}/employeeRelated/{employeeId}")
    public void deleteEmployeeFromCompany(@PathVariable Integer companyId, @PathVariable Integer employeeId) {
        companyService.removeEmployee(companyId, employeeId);
    }

    @PutMapping("/{companyId}/employeeRelated")
    public void replaceEmployees(@PathVariable Integer companyId, @RequestBody List<EmployeeDto> employeeDtoList) {
        companyService.replaceEmployees(companyId, employeeMapper.toEmployeeList(employeeDtoList));
    }

}


