package techzen.module4_c1224.repository.impl;

import org.springframework.stereotype.Repository;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.model.Gender;
import techzen.module4_c1224.repository.IDepartmentRepository;
import techzen.module4_c1224.repository.IEmployeeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository extends IEmployeeRepository {

    public EmployeeRepository(IDepartmentRepository departmentRepository) {
        List<Employee> employees = new ArrayList<>(
                Arrays.asList(
                        new Employee(UUID.fromString("a5dfdd2d-81d1-41f1-bcae-3a663f970043"), "Anh", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(1000), null, (Department) ((List<?>) departmentRepository.getData()).get(0)),
                        new Employee(UUID.fromString("1e18389d-5596-423f-ab94-27db476f4d24"), "Binh", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(2000), null, (Department) ((List<?>) departmentRepository.getData()).get(1)),
                        new Employee(UUID.fromString("581e01e2-3f6e-4f20-a052-27305599cd83"), "Cuong", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(3000), null, (Department) ((List<?>) departmentRepository.getData()).get(2))
                )
        );

        setData(employees);
    }

    @Override
    public Collection<Employee> findByAttributes(String name, LocalDate dobFrom, LocalDate dobTo, Gender gender, String salaryRange, String phone, UUID departmentId) {
        return getData().stream()
                .filter(employee -> name == null || employee.getName().contains(name))
                .filter(employee -> dobFrom == null || employee.getDob().isAfter(dobFrom))
                .filter(employee -> dobTo == null || employee.getDob().isBefore(dobTo))
                .filter(employee -> gender == null || employee.getGender().equals(gender))
                .filter(employee -> {
                    if (salaryRange == null) {
                        return true;
                    }
                    int million = 1_000_000;
                    return switch (salaryRange) {
                        case "<5tr" -> employee.getSalary().compareTo(BigDecimal.valueOf(5 * million)) < 0;
                        case "5 - <10tr" ->
                                employee.getSalary().compareTo(BigDecimal.valueOf(5 * million)) >= 0 && employee.getSalary().compareTo(BigDecimal.valueOf(10 * million)) < 0;
                        case "10 - <20tr" ->
                                employee.getSalary().compareTo(BigDecimal.valueOf(10 * million)) >= 0 && employee.getSalary().compareTo(BigDecimal.valueOf(20 * million)) < 0;
                        case "20tr up" -> employee.getSalary().compareTo(BigDecimal.valueOf(20 * million)) >= 0;
                        default -> true;
                    };
                })
                .filter(employee -> phone == null || employee.getPhone().contains(phone))
                .filter(employee -> departmentId == null || employee.getDepartment().getId().equals(departmentId))
                .collect(Collectors.toList());
    }
}
