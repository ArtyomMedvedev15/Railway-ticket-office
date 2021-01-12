package com.railwayticket.dao.dao_api;

import com.railwayticket.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BaseDaoApi<T> {
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
    T getOneById(Long id);
    List<T> FindAll();
}
