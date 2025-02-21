package techzen.module4_c1224.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzen.module4_c1224.exception.AppException;
import techzen.module4_c1224.exception.ErrorCode;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.model.Gender;
import techzen.module4_c1224.service.dto.JsonResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    Set<Employee> employees = new HashSet<>(
            Set.of(
                    new Employee(1, "Anh", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(1000), null, new Department(1, "IT")),
                    new Employee(2, "Binh", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(2000), null, new Department(2, "Finance")),
                    new Employee(3, "Cuong", LocalDate.now(), Gender.MALE, BigDecimal.valueOf(3000), null, new Department(3, "HR"))
            )
    );

    @GetMapping
    public ResponseEntity<?> getEmployees(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) LocalDate dobFrom,
                                          @RequestParam(required = false) LocalDate dobTo,
                                          @RequestParam(required = false) Gender gender,
                                          @RequestParam(required = false) String salaryRange,
                                          @RequestParam(required = false) String phone,
                                          @RequestParam(required = false) Integer departmentId) {
        Set<Employee> filteredEmployees = employees.stream()
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
                .collect(Collectors.toSet());

        return JsonResponse.ok(filteredEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        employee.setId((int) (Math.random() * 10000));
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    return JsonResponse.ok(e);
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return JsonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }
}
