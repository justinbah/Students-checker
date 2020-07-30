package dao;

import java.util.List;

public interface DAO<T> {
    T create(T bean);
    T getById(Integer id);
    List<T> getAll();
    void update(T bean);
    void delete(Integer id);
}