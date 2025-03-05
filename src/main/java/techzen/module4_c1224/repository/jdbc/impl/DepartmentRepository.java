//package techzen.module4_c1224.repository.impl;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.stereotype.Repository;
//import techzen.module4_c1224.model.Department;
//import techzen.module4_c1224.repository.IDepartmentRepository;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//@Getter
//@Setter
//public class DepartmentRepository extends IDepartmentRepository {
//    private Integer autoIncrementId = 1;
//
//    @Override
//    public Integer generateId() {
//        return autoIncrementId++;
//    }
//
//    public DepartmentRepository() {
//        List<Department> departments = new ArrayList<>(
//                Arrays.asList(
//                        new Department(generateId(), "HR"),
//                        new Department(generateId(), "IT"),
//                        new Department(generateId(), "Finance"),
//                        new Department(generateId(), "Marketing")
//                )
//        );
//
//        setData(departments);
//    }
//
//    @Override
//    public Collection<Department> findByName(String name) {
//        return getData().stream()
//                .filter(department -> department.getName().equals(name))
//                .collect(Collectors.toList());
//    }
//
//}
