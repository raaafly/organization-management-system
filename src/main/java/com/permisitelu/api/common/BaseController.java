package com.permisitelu.api.common;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(Long id);
    ResponseEntity<T> create(T object);
    ResponseEntity<T> update(Long id, T object);
    ResponseEntity<ResponseMessage> delete(Long id);
}
