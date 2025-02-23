package techzen.module4_c1224.repository;

import lombok.Getter;
import lombok.Setter;
import techzen.module4_c1224.model.IEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public abstract class ICommonRepository<T extends IEntity<ID>, ID> {

    private Collection<T> data;

    public UUID generateId() {
        return UUID.randomUUID();
    }

    public Collection<T> findAll() {
        return data;
    }

    public Optional<T> findById(ID id) {
        return data.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public T save(T t) {
        return findById(t.getId())
                .map(e -> {
                    data.remove(e);
                    data.add(t);
                    return t;
                })
                .orElseGet(() -> {
                    data.add(t);
                    return t;
                });
    }

    public boolean deleteById(ID id) {
        return findById(id)
                .map(e -> data.remove(e))
                .orElse(false);
    }
}
