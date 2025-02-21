package techzen.module4_c1224.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public enum ErrorCode {
    EMPLOYEE_NOT_EXIST(40401, "Employee does not exist", HttpStatus.NOT_FOUND),
    STUDENT_NOT_EXIST(40402, "Student does not exist", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXIST(40403, "Department does not exist", HttpStatus.NOT_FOUND),
    ;
    int code;
    String message;
    HttpStatus status;
}
