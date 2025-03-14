package techzen.module4_c1224.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.model.Gender;

public class EmployeeSpec {

    public static Specification<Employee> hasName(String name) {
        return (root, query, criteriaBuilder) -> name == null ? null
                : criteriaBuilder.like(root.get("name"), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Employee> hasDobFrom(LocalDate dobFrom) {
        return (root, query, criteriaBuilder) -> dobFrom == null ? null
                : criteriaBuilder.greaterThanOrEqualTo(root.get("dob"), dobFrom);
    }

    public static Specification<Employee> hasDobTo(LocalDate dobTo) {
        return (root, query, criteriaBuilder) -> dobTo == null ? null
                : criteriaBuilder.lessThanOrEqualTo(root.get("dob"), dobTo);
    }

    public static Specification<Employee> hasGender(Gender gender) {
        return (root, query, criteriaBuilder) -> gender == null ? null
                : criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<Employee> hasPhone(String phone) {
        return (root, query, criteriaBuilder) -> phone == null ? null
                : criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Employee> hasDepartmentId(Integer departmentId) {
        return (root, query, criteriaBuilder) -> departmentId == null ? null
                : criteriaBuilder.equal(root.get("department").get("id"), departmentId);
    }

    public static Specification<Employee> hasSalaryInRange(String salaryRange) {
        return (root, query, criteriaBuilder) -> {
            if (salaryRange == null) {
                return null;
            }
            return switch (salaryRange) {
                case "lt5" -> criteriaBuilder.lessThan(root.get("salary"), 5000);
                case "5to10" -> criteriaBuilder.between(root.get("salary"), 5000, 10000);
                case "10to20" -> criteriaBuilder.between(root.get("salary"), 10000, 20000);
                case "gt20" -> criteriaBuilder.greaterThan(root.get("salary"), 20000);
                default -> null;
            };
        };
    }
}