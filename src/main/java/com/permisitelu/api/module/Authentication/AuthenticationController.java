package com.permisitelu.api.module.Authentication;

import com.permisitelu.api.common.ResponseMessage;
import com.permisitelu.api.module.Authentication.payload.LoginDTO;
import com.permisitelu.api.module.Authentication.payload.RegisterDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Authentication")
@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final AuthenticationManager authManager;

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody @Valid RegisterDTO object) throws RuntimeException {
        RegisterDTO data = service.register(object);
        String activationToken = service.createActivationToken(data);

        Map<String, Object> payload = new HashMap<>();
        payload.put("email", data.getEmail());
        payload.put("activationToken", activationToken);

        ResponseMessage message = new ResponseMessage("Registration successfully!", payload, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody @Valid LoginDTO object) throws RuntimeException {
        LoginDTO data = service.login(object);
        String accessToken = service.createAccessToken(data);
        String refreshToken = service.createRefreshToken(data);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(data.getEmail(), object.getPassword());
        Authentication authentication = authManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseCookie cookie = ResponseCookie
                .from("Permisi-Session", accessToken)
                .path("/api/v1")
                .maxAge(24 * 60 * 60)
                .httpOnly(true)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());

        Map<String, Object> payload = new HashMap<>();
        payload.put("email", data.getEmail());
        payload.put("accessToken", accessToken);
        payload.put("refreshToken", refreshToken);

        ResponseMessage message = new ResponseMessage("Login successfully!", payload, HttpStatus.OK);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
