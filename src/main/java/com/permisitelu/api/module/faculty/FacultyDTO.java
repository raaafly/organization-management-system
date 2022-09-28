package com.permisitelu.api.module.faculty;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Faculty} entity
 */
@Data
public class FacultyDTO implements Serializable {
    private String name;
}