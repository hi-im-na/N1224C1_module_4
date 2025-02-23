package techzen.module4_c1224.service;

import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.service.dto.EmployeeSearchRequest;

import java.util.Collection;
import java.util.UUID;

public interface IEmployeeService extends ICommonService<Employee, UUID> {
    Collection<Employee> findByAttributes(EmployeeSearchRequest attr);
}
