package techzen.module4_c1224.service.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import techzen.module4_c1224.model.Gender;

import java.time.LocalDate;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeSearchRequest {
    String name;
    LocalDate dobFrom;
    LocalDate dobTo;
    Gender gender;
    String salaryRange;
    String phone;
    Integer departmentId;
}
