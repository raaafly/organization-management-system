package com.permisitelu.api.common;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T> {
    ResponseEntity<ResponseMessage> getAll();
    ResponseEntity<ResponseMessage> getById(Long id);
    ResponseEntity<ResponseMessage> create(T object);
    ResponseEntity<ResponseMessage> update(Long id, T object);
    ResponseEntity<ResponseMessage> delete(Long id);
}
