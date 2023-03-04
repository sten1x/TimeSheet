package jdbc.dao;

import java.util.List;

public interface IDAO<T> {
    List<T> GetAll();

    T getById(final Integer id);

    boolean update(final T object);

    boolean create(final T object);

    boolean delete(final T object);
}
