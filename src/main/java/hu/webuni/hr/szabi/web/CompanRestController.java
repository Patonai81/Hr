package hu.webuni.hr.szabi.web;

import hu.webuni.hr.szabi.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RestController()
@DependsOn("employeeRest")
@RequestMapping("/api/companies")
public class CompanRestController {

    @Autowired
    EmployeeRestController employeeRestController;

    Map<Integer,CompanyDto> companies = new HashMap<>();

    @PostConstruct
    public void init() {
        companies.put(1,new CompanyDto("CompanyId","Name","Address",employeeRestController.getEmployeeList()));

    }

    @RequestMapping( value = {"","/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompany(@RequestParam(name = "full", required = false) String full, @PathVariable Optional<Integer> id){
       if (!id.isPresent()) {
        if(null == full || "false".equalsIgnoreCase(full)){
            companies.values().stream().forEach( companyDto -> companyDto.setEmployeesList(null));
        }
        return ResponseEntity.ok(companies.values().stream().collect(Collectors.toList()));
       } else {
           return companies.containsKey(id.get()) ? ResponseEntity.ok(Arrays.asList(companies.get(id.get()))) : ResponseEntity.noContent().build();
       }

    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer id) {
        return companies.containsKey(id) ? ResponseEntity.ok(companies.get(id)) : ResponseEntity.noContent().build();
    }

     */
}
