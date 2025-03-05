package techzen.module4_c1224.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import techzen.module4_c1224.model.Department;
import techzen.module4_c1224.service.dto.req.DepartmentReqDto;
import techzen.module4_c1224.service.dto.res.DepartmentResDto;

@Mapper(componentModel = "spring")
public interface IDepartmentMapper {
    Department toEntity(DepartmentReqDto departmentReqDto);

    Department toEntity(DepartmentResDto departmentResDto);

    DepartmentResDto toResDto(Department department);

    DepartmentReqDto toReqDto(Department department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentReqDto departmentReqDto, @MappingTarget Department department);
}
