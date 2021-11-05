package hu.webuni.hr.szabi.repository;

import hu.webuni.hr.szabi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Long countEmployeeByIdEquals(Long id);
    List<Employee> whatIs();

    @Query("select e from Employee e where upper(e.assignment) = upper(?1) order by e.employeeName")
    List<Employee> employeeFinderByAssignment(String assignment);

    List<Employee> findByAssignmentEqualsIgnoreCaseOrderByEmployeeNameAsc(@NonNull String assignment);

    List<Employee> findByEmployeeNameStartsWithIgnoreCaseOrderByEmployeeNameDesc(@NonNull String employeeName);

    @Query("select e from Employee e where e.startWork between :startWorkStart and :startWorkEnd")
    List<Employee> findEmployeesBetweenStartDates(@Param("startWorkStart") @NonNull LocalDateTime startWorkStart, @Param("startWorkEnd") @NonNull LocalDateTime startWorkEnd);



}
