package techzen.module4_c1224.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techzen.module4_c1224.model.Department;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByName(String name);
}
