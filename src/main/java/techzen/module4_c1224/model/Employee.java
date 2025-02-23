package techzen.module4_c1224.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements IEntity<UUID> {
    private UUID id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private BigDecimal salary;
    private String phone;
    private Department department;
}
