package hu.webuni.hr.szabi.repository;

import hu.webuni.hr.szabi.model.Company;
import hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Long> {

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
    @Query("select new hu.webuni.hr.szabi.repository.result.CompanyBYAVGSalaryResult( c.name,employeesList.assignment,avg(employeesList.salary)) from Company c left join c.employeesList employeesList group by c.name,employeesList.assignment order by avg (employeesList.salary) desc ")
    List<CompanyBYAVGSalaryResult> queryCompanyListAggregatedByAssignmentAndAvgSalaryOrderByAvgSalaryDesc();


}
