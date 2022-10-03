package com.permisitelu.api.provider;

import com.permisitelu.api.utility.Constant;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class TokenProvider {
    public String getSubjectFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Constant.SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "Permisi-Session");
        if (cookie != null) return cookie.getValue();
        return null;
    }

    public String doParsingToken(HttpServletRequest request) {
        String token = getTokenFromCookie(request);
        return token;
    }

    public boolean doValidateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Constant.SECRET_KEY).build();
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
