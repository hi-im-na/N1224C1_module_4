package techzen.module4_c1224.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.service.dto.req.EmployeeSearchRequest;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("""
            select e from Employee e
            where (:#{#request.name} is null or e.name like %:#{#request.name}%) and
            (:#{#request.dobFrom} is null or e.dob >= :#{#request.dobFrom}) and
            (:#{#request.dobTo} is null or e.dob <= :#{#request.dobTo}) and
            (:#{#request.gender} is null or e.gender = :#{#request.gender}) and
            (:#{#request.phone} is null or e.phone = :#{#request.phone}) and
            (:#{#request.departmentId} is null or e.department.id = :#{#request.departmentId}) and
            (
                :#{#request.salaryRange} is null or
                (:#{#request.salaryRange} like '%lt5%' and e.salary < 5000000) or
                (:#{#request.salaryRange} like '%5-10%' and e.salary >= 5000000 and e.salary < 10000000) or
                (:#{#request.salaryRange} like '%10-20%' and e.salary >= 10000000 and e.salary < 20000000) or
                (:#{#request.salaryRange} like '%gt20%' and e.salary >= 20000000)
            )
            """)
    Page<Employee> findByAttributes(EmployeeSearchRequest request, Pageable pageable);
}
