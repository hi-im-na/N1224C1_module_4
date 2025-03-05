package techzen.module4_c1224.service.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzen.module4_c1224.model.Gender;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link techzen.module4_c1224.model.Employee}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeReqDto implements Serializable {
    private String name;
    private LocalDate dob;
    private Gender gender;
    private BigDecimal salary;
    private String phone;
    private Integer departmentId;
}