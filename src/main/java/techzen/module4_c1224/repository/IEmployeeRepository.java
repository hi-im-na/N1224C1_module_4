package techzen.module4_c1224.repository;

import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.model.Gender;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public abstract class IEmployeeRepository extends ICommonRepository<Employee, UUID> {
    public abstract Collection<Employee> findByAttributes(String name, LocalDate dobFrom, LocalDate dobTo, Gender gender, String salaryRange, String phone, UUID departmentId);
}
