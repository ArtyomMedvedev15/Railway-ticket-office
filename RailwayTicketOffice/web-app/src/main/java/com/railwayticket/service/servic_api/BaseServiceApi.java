package com.railwayticket.service.servic_api;

import com.railwayticket.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BaseServiceApi<T> {
    boolean save(T t) throws ServiceException;
    boolean update(T t) throws ServiceException;
    boolean delete(T t) throws ServiceException;
    T getOneById(Long id) throws ServiceException;
    List<T> FindAll();
}
