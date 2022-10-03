package com.permisitelu.api.module.Authentication.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
