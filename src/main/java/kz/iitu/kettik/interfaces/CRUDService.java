package kz.iitu.kettik.interfaces;

import java.util.List;

public interface CRUDService<T> {
    T create(T t);

    List<T> read();
    T read(Integer id);

    T update(Integer id, T t);

    void delete(Integer id);
}
