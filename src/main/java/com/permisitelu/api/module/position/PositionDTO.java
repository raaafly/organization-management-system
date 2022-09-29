package com.permisitelu.api.module.position;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Position} entity
 */
@Data
public class PositionDTO implements Serializable {
    private final String name;
}