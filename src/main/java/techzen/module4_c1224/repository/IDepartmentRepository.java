package techzen.module4_c1224.repository;

import techzen.module4_c1224.model.Department;

import java.util.Collection;
import java.util.UUID;

public abstract class IDepartmentRepository extends ICommonRepository<Department, UUID> {
    public abstract Collection<Department> findByName(String name);
}
