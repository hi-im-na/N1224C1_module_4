package techzen.module4_c1224.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techzen.module4_c1224.exception.AppException;
import techzen.module4_c1224.exception.ErrorCode;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.repository.IDepartmentRepository;
import techzen.module4_c1224.service.IDepartmentService;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;

    @Override
    public Collection<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(UUID uuid) {
        return departmentRepository.findById(uuid)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @Override
    public Department save(Department department) {
        department.setId(departmentRepository.generateId());
        return departmentRepository.save(department);
    }

    @Override
    public Department update(UUID uuid, Department department) {
        Department existingDepartment = departmentRepository.findById(uuid)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
        existingDepartment.setName(department.getName());
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteById(UUID uuid) {
        boolean isDeleted = departmentRepository.deleteById(uuid);
        if (!isDeleted) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
    }
}
