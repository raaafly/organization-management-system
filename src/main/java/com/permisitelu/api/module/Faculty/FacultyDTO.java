package com.permisitelu.api.module.Faculty;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Faculty} entity
 */
@Data
public class FacultyDTO implements Serializable {
    private Long id;
    private String name;
}