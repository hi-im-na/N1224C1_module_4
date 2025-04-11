package techzen.module4_c1224.service.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzen.module4_c1224.model.Gender;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link techzen.module4_c1224.model.Employee}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResDto implements Serializable {
    private UUID id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private BigDecimal salary;
    private String phone;
    private String username;
    private Set<RoleResDto> roles;
}