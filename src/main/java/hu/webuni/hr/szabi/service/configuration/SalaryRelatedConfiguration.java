package hu.webuni.hr.szabi.service.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties("hr")
@Validated
public class SalaryRelatedConfiguration {

    public Map<String, EmployeeService> getMyList() {
        return myList;
    }

    public void setMyList(Map<String, EmployeeService> myList) {
        this.myList = myList;
    }

    //hr.mylist= {'KEY1',${hr.employeeService[0]}}
    private Map<String, EmployeeService> myList = new HashMap<>();

    @NotEmpty
    List<EmployeeService> employeeService = new ArrayList<>(6);

    @Max(100)
    private int maxPercentage;

    public List<EmployeeService> getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(List<EmployeeService> employeeService) {
        this.employeeService = employeeService;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }

    public void setMaxPercentage(int maxPercentage) {
        this.maxPercentage = maxPercentage;
    }

    public static class EmployeeService {

        @Max(1000)
        private int maxmonth;

        @Max(100)
        @Min(0)
        private int percentage;

        public int getMaxmonth() {
            return maxmonth;
        }

        public void setMaxmonth(int maxmonth) {
            this.maxmonth = maxmonth;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }
    }


}
