package com.permisitelu.api.module.User;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserDTO implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
}