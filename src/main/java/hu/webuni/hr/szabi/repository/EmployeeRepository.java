package hu.webuni.hr.szabi.repository;

import hu.webuni.hr.szabi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Long countEmployeeByIdEquals(Long id);
    List<Employee> whatIs();

    @Query("select e from Employee e where e.salary > :salary")
    List<Employee> findEmployeeSalaryGreaterThan(@Param("salary") Integer salary);

    @Query("select e from Employee e where upper(e.position.positionName) = upper(?1) order by e.employeeName")
    List<Employee> employeeFinderByPosition(String position);

    List<Employee> findByPosition_PositionNameIgnoreCase(String positionName);

    List<Employee> findByEmployeeNameStartsWithIgnoreCaseOrderByEmployeeNameDesc(@NonNull String employeeName);

    @Query("select e from Employee e where e.startWork between :startWorkStart and :startWorkEnd")
    List<Employee> findEmployeesBetweenStartDates(@Param("startWorkStart") @NonNull LocalDateTime startWorkStart, @Param("startWorkEnd") @NonNull LocalDateTime startWorkEnd);

    @Query("select e from Employee e where e.employeeName = ?1")
    @NonNull
    Optional<Employee> findEmployeebyName(String employeeName);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.salary=:salaryMin WHERE e.id IN (SELECT e2.id from Employee e2" +
            " where e2.position.positionName = :positionName AND e2.salary < :salaryMin) ")
    void updateEmployeeSalary(@Param("positionName") String positionName,@Param("salaryMin") Integer salaryMin);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.salary=:salaryMin WHERE e.id IN (SELECT e2.id from Employee e2" +
            " where e2.position.positionName = :positionName AND e2.salary < :salaryMin AND e2.companyToWorkFor.companyId = :companyId) ")
    void updateEmployeeSalaryByCompanyId(@Param("positionName") String positionName,@Param("salaryMin") Integer salaryMin, @Param("companyId") Long companyId);


}
