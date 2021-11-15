package hu.webuni.hr.szabi.repository;

import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {


    //Ez sajnos nem működik így
    @Query(value = "SELECT * FROM Company c LEFT  JOIN Employee e ON c.company_id = e.company_to_work_for_company_id",
            countQuery = "SELECT count(*) FROM Company c",
            nativeQuery = true)
    Page<Company> findCompaniesWithEmployeesWithPaginationNative(Pageable pageable);

    @EntityGraph("Company.companyWithEmployees")
    //@EntityGraph(attributePaths = {"employeesList","companyTypeFromDB"})
    @Query(value = "select c FROM Company c")
    Page<Company> findCompaniesWithEmployees(Pageable pageable);


    @Query("select c FROM Company c LEFT JOIN FETCH c.employeesList")
    List<Company> findCompaniesWithEmployees();

    @Query("select c from Company c where c.name = :companyName")
    Optional<Company> findCompanyByName(@Param("companyName") String companyName);

    @EntityGraph(attributePaths = {"employeesList","companyTypeFromDB"})
    @Query("select c from Company c where c.name = :companyName")
    Optional<Company> findCompanyByNameWithEmployeeList(@Param("companyName") String companyName);

    List<Company> findDistinctByEmployeesList_SalaryGreaterThanOrderByNameAsc(@NonNull Integer salary);

    /*
    select  c.name, count(e.company_to_work_for_company_id) from company c INNER JOIN employee e on c.company_id = e.company_to_work_for_company_id
    group by (c.name)
    having count(e.company_to_work_for_company_id) >1;
     */
    @Query("select c from Company c left join c.employeesList employeesList group by c having count(employeesList.companyToWorkFor) > :numberOfEmployees")
    List<Company> queryCompanyListWhereEmployeeNumberGt(@Param("numberOfEmployees") Long numberOfEmployees);

    /*
    select  c.name,e.assignment,count(e.assignment), avg(e.salary) from company c INNER JOIN employee e on c.company_id = e.company_to_work_for_company_id
    group by (c.name), (e.assignment)
    order by avg(e.salary) desc;
     */
    @Query("select new hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult( c.name,employeesList.position.positionName,avg(employeesList.salary)) from Company c left join c.employeesList employeesList group by c.name,employeesList.position.positionName order by avg (employeesList.salary) desc ")
    List<CompanyBYAVGSalaryResult> queryCompanyListAggregatedByAssignmentAndAvgSalaryOrderByAvgSalaryDesc();


}
