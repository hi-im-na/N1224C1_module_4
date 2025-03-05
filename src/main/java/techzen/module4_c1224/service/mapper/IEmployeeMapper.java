package techzen.module4_c1224.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import techzen.module4_c1224.model.Employee;
import techzen.module4_c1224.service.dto.req.EmployeeReqDto;
import techzen.module4_c1224.service.dto.res.EmployeeResDto;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
    Employee toEntity(EmployeeReqDto employeeReqDto);

    Employee toEntity(EmployeeResDto employeeResDto);

    EmployeeReqDto toReqDto(Employee employee);

    EmployeeResDto toResDto(Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee partialUpdate(EmployeeReqDto employeeReqDto, @MappingTarget Employee employee);


}
