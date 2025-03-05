package techzen.module4_c1224.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department implements IEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("department")
    private List<Employee> employees;

    public Department(Integer integer, String name) {
    }

    @Override
    public Integer getId() {
        return id;
    }
}
