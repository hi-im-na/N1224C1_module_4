package techzen.module4_c1224.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.service.dto.req.EmployeeSearchRequest;

public interface IEmployeeService extends ICommonService<Employee, UUID> {
    Page<Employee> findByAttributes(EmployeeSearchRequest attr, Pageable pageable);
}
