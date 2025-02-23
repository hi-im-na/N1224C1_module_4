package techzen.module4_c1224.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import techzen.module4_c1224.exception.AppException;
import techzen.module4_c1224.exception.ErrorCode;
import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.repository.IEmployeeRepository;
import techzen.module4_c1224.service.IDepartmentService;
import techzen.module4_c1224.service.IEmployeeService;
import techzen.module4_c1224.service.dto.EmployeeSearchRequest;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "employees")
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final IDepartmentService departmentService;

    @Override
    public Collection<Employee> findAll() {
        return employeeRepository.findAll();
    }


    @Override
    public Collection<Employee> findByAttributes(EmployeeSearchRequest attr) {
        return employeeRepository.findByAttributes(attr.getName(), attr.getDobFrom(), attr.getDobTo(), attr.getGender(), attr.getSalaryRange(), attr.getPhone(), attr.getDepartmentId());
    }

    @Override
    @Cacheable(key = "#id")
    public Employee findById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @Override
    @CachePut(key = "#result.id")
    public Employee save(Employee employee) {
        try {
            employee.setId(employeeRepository.generateId());
            employee.setDepartment(departmentService.findById(employee.getDepartment().getId())); // check if department exists
            return employeeRepository.save(employee);
        } catch (AppException e) {
            throw new AppException(ErrorCode.DEPARTMENT_ID_NOT_VALID);
        }
    }


    @Override
    @CachePut(key = "#id")
    public Employee update(UUID id, Employee employee) {
        try {
            Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
            existingEmployee.setDepartment(departmentService.findById(employee.getDepartment().getId())); // check if department exists
            existingEmployee.setDob(employee.getDob());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setPhone(employee.getPhone());

            return employeeRepository.save(existingEmployee);
        } catch (AppException e) {
            throw new AppException(ErrorCode.DEPARTMENT_ID_NOT_VALID);
        }
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteById(UUID id) {
        boolean isDeleted = employeeRepository.deleteById(id);
        if (!isDeleted) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
    }
}