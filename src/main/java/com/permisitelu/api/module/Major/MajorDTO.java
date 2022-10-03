package com.permisitelu.api.module.Major;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Major} entity
 */
@Data
public class MajorDTO implements Serializable {
    private Long id;
    private String name;
    private String facultyName;
}