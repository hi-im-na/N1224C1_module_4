package techzen.module4_c1224.repository.impl;

import org.springframework.stereotype.Repository;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.repository.IDepartmentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DepartmentRepository extends IDepartmentRepository {

    public DepartmentRepository() {
        List<Department> departments = new ArrayList<>(
                Arrays.asList(
                        new Department(UUID.fromString("8ae5f7b5-65a3-4578-a640-b7bdfd463f9d"), "HR"),
                        new Department(UUID.fromString("046290b0-2d0f-40ad-b6c9-3cfce52dfef7"), "IT"),
                        new Department(UUID.fromString("0ca1ca8f-c9af-48ef-9c6b-90fdc336d8af"), "Finance"),
                        new Department(UUID.fromString("885df22a-144e-4112-9cdd-fcb7b1a3875b"), "Marketing")
                )
        );

        setData(departments);
    }

    @Override
    public Collection<Department> findByName(String name) {
        return getData().stream()
                .filter(department -> department.getName().equals(name))
                .collect(Collectors.toList());
    }
}
