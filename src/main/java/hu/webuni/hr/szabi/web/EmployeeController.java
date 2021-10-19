package hu.webuni.hr.szabi.web;


import hu.webuni.hr.szabi.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping
    public String homePage(){
        return "index";
    }

    @GetMapping("/employeeManager")
    public String listEmployees(ModelMap model) {

       // employeeList.add(new Employee(1l, "Szabi", "Worker", 1000, LocalDateTime.of(2021, 4, 13, 8, 0, 0)));

        model.put("employeeList", employeeList);
        model.put("newEmployee", new Employee());
        return "employeeList";
    }

    @PostMapping("/employeeManager")
    public String addEmployee(Employee employee) {
        if (employee.getId()!= null && !employeeList.stream().anyMatch( e -> employee.getId()== e.getId()))
            employeeList.add(employee);

        return "redirect:employeeManager";
    }

    @GetMapping("/employeeManager/{id}")
    public ModelAndView getEmployee(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("employeeDetail");
        modelAndView.addObject("myId",id);
        modelAndView.addObject("employeeDetail",employeeList.stream().filter( e -> e.getId() == id.longValue()).findFirst().get());
        return modelAndView;
    }

    @PostMapping("/employeeManager/{id}")
    public String modifyEmployee(@PathVariable Integer id,Employee employee){

        employeeList.removeIf( e -> e.getId()== employee.getId());
        employeeList.add(employee);

        return "redirect:/employeeManager/"+id;

    }



}
