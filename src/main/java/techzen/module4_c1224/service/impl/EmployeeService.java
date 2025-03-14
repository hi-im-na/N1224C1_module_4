package techzen.module4_c1224.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import techzen.module4_c1224.exception.AppException;
import techzen.module4_c1224.exception.ErrorCode;
import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.repository.IEmployeeRepository;
import techzen.module4_c1224.repository.specification.EmployeeSpec;
import techzen.module4_c1224.service.IDepartmentService;
import techzen.module4_c1224.service.IEmployeeService;
import techzen.module4_c1224.service.dto.req.EmployeeReqDto;
import techzen.module4_c1224.service.dto.req.EmployeeSearchRequest;
import techzen.module4_c1224.service.mapper.IEmployeeMapper;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "employees")
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final IDepartmentService departmentService;
    private final IEmployeeMapper employeeMapper;

    @Override
    public Collection<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findByAttributes(EmployeeSearchRequest attr, Pageable pageable) {
        // return employeeRepository.findByAttributes(attr, pageable);
        Specification<Employee> spec = Specification.where(EmployeeSpec.hasName(attr.getName()))
                .and(EmployeeSpec.hasDobFrom(attr.getDobFrom()))
                .and(EmployeeSpec.hasDobTo(attr.getDobTo()))
                .and(EmployeeSpec.hasGender(attr.getGender()))
                .and(EmployeeSpec.hasPhone(attr.getPhone()))
                .and(EmployeeSpec.hasDepartmentId(attr.getDepartmentId()))
                .and(EmployeeSpec.hasSalaryInRange(attr.getSalaryRange()));
        return employeeRepository.findAll(spec, pageable);
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
            employee.setDepartment(departmentService.findById(employee.getDepartment().getId())); // check if department
                                                                                                  // exists
            return employeeRepository.save(employee);
        } catch (AppException e) {
            throw new AppException(ErrorCode.DEPARTMENT_ID_NOT_VALID);
        }
    }

    @Override
    @CachePut(key = "#id")
    public Employee update(UUID id, Employee employee) {
        try {
            Employee existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
            existingEmployee.setDepartment(departmentService.findById(employee.getDepartment().getId())); // check if
                                                                                                          // department
                                                                                                          // exists
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
        if (!employeeRepository.existsById(id)) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee save(EmployeeReqDto employeeReqDto) {
        Employee employee = employeeMapper.toEntity(employeeReqDto);
        return employeeRepository.save(employee);
    }
}