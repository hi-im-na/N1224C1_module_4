package techzen.module4_c1224.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@MappedSuperclass
@SQLDelete(sql = "UPDATE ${table} SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public abstract class BaseEntity {
    private boolean deleted = false;
}
