package techzen.module4_c1224.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements IEntity<UUID> {
    private UUID id;
    private String name;
}
