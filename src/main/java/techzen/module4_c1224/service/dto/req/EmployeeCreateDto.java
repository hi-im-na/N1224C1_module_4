package techzen.module4_c1224.service.dto.req;

import lombok.Data;
import techzen.module4_c1224.model.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class EmployeeCreateDto {
    private UUID id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private BigDecimal salary;
    private String phone;
    private Integer departmentId;
}
