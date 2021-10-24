package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.CompanyDto;
import hu.webuni.hr.szabi.dto.EmployeeDto;
import hu.webuni.hr.szabi.dto.mytype.CompanyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController()
@DependsOn("employeeRest")
@RequestMapping("/api/companies")
public class CompanRestController {

    @Autowired
    EmployeeRestController employeeRestController;

    Map<Integer, CompanyDto> companies = new CompanyMap();

    @PostConstruct
    public void init() {
        companies.put(1, new CompanyDto(1, "Name", "Address", employeeRestController.getEmployeeList()));

    }

    @RequestMapping(value = {"", "/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompany(@RequestParam(name = "full", required = false) String full, @PathVariable Optional<Integer> id) {
        if (!id.isPresent()) {
            if (null == full || "false".equalsIgnoreCase(full)) {
                return ResponseEntity.ok(companies.values().stream().map(CompanyDto::new).collect(Collectors.toList()));
            }
            return ResponseEntity.ok(companies.values().stream().collect(Collectors.toList()));
        } else {
            return companies.containsKey(id.get()) ? ResponseEntity.ok(Arrays.asList(companies.get(id.get()))) : ResponseEntity.noContent().build();
        }

    }


    //másik megoldás külön metódusban
    /*
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer id) {
        return companies.containsKey(id) ? ResponseEntity.ok(companies.get(id)) : ResponseEntity.noContent().build();
    }

     */

    @PostMapping
    public ResponseEntity<CompanyDto> addNewCompany(@RequestBody CompanyDto companyDto) {

        if (companies.containsKey(companyDto.getCompanyId())) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            companies.put(Integer.valueOf(companyDto.getCompanyId()), companyDto);
        }
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable Integer id,@RequestBody CompanyDto companyDto) {

        if (!companies.containsKey(id)) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            companies.put(Integer.valueOf(companyDto.getCompanyId()), companyDto);
        }
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companies.remove(id);
    }


    @PostMapping("/{companyId}/employeeRelated")
    public EmployeeDto addNewEmployeeToCompany(@PathVariable Integer companyId,@RequestBody EmployeeDto employeeDto){
        CompanyDto companyDto = companies.get(companyId);
        companyDto.getEmployeesList().add(employeeDto);
        return employeeDto;
    }


    @DeleteMapping("/{companyId}/employeeRelated/{employeeId}")
    public void deleteEmployeeFromCompany(@PathVariable Integer companyId,@PathVariable Integer employeeId) {
        //Null előtt eldobjuk a hibát
        List<EmployeeDto> employeeDtoList = companies.get(companyId).getEmployeesList();
    //    employeeDtoList.removeIf(e -> e.getId().intValue() == employeeId.intValue());

        IntStream.range(0, employeeDtoList.size())
                .filter(i -> employeeDtoList.get(i).getId().intValue() ==employeeId)
                .boxed()
                .findFirst()
                .map(i -> employeeDtoList.remove((int) i));

    }

    @PutMapping("/{companyId}/employeeRelated")
    public void replaceEmployees(@PathVariable Integer companyId,@RequestBody List<EmployeeDto> employeeDtoList){
        CompanyDto companyDto = companies.get(companyId);
        companyDto.setEmployeesList(employeeDtoList);
    }
}
