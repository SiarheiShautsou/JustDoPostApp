package by.sheva.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<K, T> {

    int DEFAULT_FIND_ALL_LIMIT = 10;

    int DEFAULT_FIND_ALL_OFFSET = 0;

    T findById(K id) throws SQLException;

    Optional<T> findOne(K id);

    List<T> findAll();

    List<T> findAll(int limit, int offset);

    T creatObject(T object);

    T updateObject(T object);

    K deleteObject(K id);
}
