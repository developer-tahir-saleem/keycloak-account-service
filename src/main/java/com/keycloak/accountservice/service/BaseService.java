package com.keycloak.accountservice.service;


import com.keycloak.accountservice.model.BaseModel;

import java.util.List;
import java.util.UUID;

/**
 * Created by suraksha-pnc on 2/7/19.
 */
//@NoRepositoryBean
public interface BaseService<T extends BaseModel> {
    public T insert(T item);
    public List<T> findAll();
    public List<T> findAllByDeleted();
    public T findById(UUID id);
    public T delete(UUID id);
    public T deleteSoft(UUID id);
    public T update(T item);
}
