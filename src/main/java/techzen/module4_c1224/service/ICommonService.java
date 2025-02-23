package techzen.module4_c1224.service;

import java.util.Collection;

public interface ICommonService<T, ID> {
    Collection<T> findAll();

    T findById(ID id);

    T save(T t);

    T update(ID id, T t);

    void deleteById(ID id);
}
