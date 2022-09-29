package com.permisitelu.api.module.department;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Department} entity
 */
@Data
public class DepartmentDTO implements Serializable {
    private String name;
}