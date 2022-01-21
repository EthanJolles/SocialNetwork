package com.solvd.socialNetwork.dao.interfaces;


import java.sql.SQLException;

public interface IBaseDao<T> {
    void create(T t) throws SQLException;
    T getById(Long id) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
}
