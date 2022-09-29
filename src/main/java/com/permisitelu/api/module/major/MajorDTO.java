package com.permisitelu.api.module.major;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Major} entity
 */
@Data
public class MajorDTO implements Serializable {
    private String name;
    private String facultyName;
}