package com.permisitelu.api.module.Authentication;

import com.permisitelu.api.module.Authentication.payload.LoginDTO;
import com.permisitelu.api.module.Authentication.payload.RegisterDTO;

public interface AuthenticationService {
    RegisterDTO register(RegisterDTO object);
    LoginDTO login(LoginDTO object);
    String createActivationToken(RegisterDTO object);
    String createAccessToken(LoginDTO object);
    String createRefreshToken(LoginDTO object);
    void sendEmailAccountVerification(String to);
}
