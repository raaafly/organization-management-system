package com.permisitelu.api.module.Authentication.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    private String email;
    private String password;
}
