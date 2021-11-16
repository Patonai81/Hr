package hu.webuni.hr.szabi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.webuni.hr.szabi.validation.RealSalary;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {


    /**
     * Internal identifier
     */
    @EqualsAndHashCode.Exclude Long id;

    /**
     * Name of employee
     */
    @NotEmpty
    String employeeName;


    /**
     * Current salarí of employee
     */
    @RealSalary
    Integer salary;

    /**
     * Time of employee entry
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Future
    public LocalDateTime startWork;


    public EmployeeDto(EmployeeDto newEmp){
        this.employeeName = newEmp.employeeName;
        this.salary = newEmp.salary;
        this.startWork = newEmp.startWork;
    }


    public EmployeeDto(String employeeName, int salary, LocalDateTime startWork) {
        this.employeeName=employeeName;
        this.salary=salary;
        this.startWork=startWork;
    }
}
