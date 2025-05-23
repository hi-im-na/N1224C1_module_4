package techzen.module4_c1224.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techzen.module4_c1224.exception.AppException;
import techzen.module4_c1224.exception.ErrorCode;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.repository.IDepartmentRepository;
import techzen.module4_c1224.service.IDepartmentService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;

    @Override
    public Collection<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Integer id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
        existingDepartment.setName(department.getName());
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteById(Integer id) {
        if (!departmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
        departmentRepository.deleteById(id);
    }
}
