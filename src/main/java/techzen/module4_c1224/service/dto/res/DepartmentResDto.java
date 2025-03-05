package techzen.module4_c1224.service.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link techzen.module4_c1224.model.Department}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentResDto implements Serializable {
    private Integer id;
    private String name;
    private List<EmployeeResDto> employees;
}