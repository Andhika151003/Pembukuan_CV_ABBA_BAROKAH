package com.pembukuan_cv_abba_barokah.DAO;

import java.util.List;

public interface BaseDao<T> {
    List<T> getAll();
    T getById(int id);
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(int id);
}
