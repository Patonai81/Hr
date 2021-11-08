package hu.webuni.hr.szabi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.webuni.hr.szabi.validation.RealSalary;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    String employeeName;

    /**
     * Actual assignment of employee
     */
    @NotEmpty
    @NonNull
    String assignment;

    /**
     * Current salarí of employee
     */
    @RealSalary
    @NonNull
    Integer salary;

    /**
     * Time of employee entry
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Future
    @NonNull
    public LocalDateTime startWork;


    public EmployeeDto(EmployeeDto newEmp){
        this.employeeName = newEmp.employeeName;
        this.assignment = newEmp.assignment;
        this.salary = newEmp.salary;
        this.startWork = newEmp.startWork;
    }


}
