package com.permisitelu.api.module.Authentication;

import com.permisitelu.api.exception.FoundException;
import com.permisitelu.api.exception.NotFoundException;
import com.permisitelu.api.module.Authentication.payload.LoginDTO;
import com.permisitelu.api.module.Authentication.payload.RegisterDTO;
import com.permisitelu.api.module.Membership.Membership;
import com.permisitelu.api.module.Membership.MembershipRepository;
import com.permisitelu.api.module.Role.Role;
import com.permisitelu.api.module.Role.RoleRepository;
import com.permisitelu.api.module.User.User;
import com.permisitelu.api.module.User.UserRepository;
import com.permisitelu.api.utility.Constant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MembershipRepository membershipRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public RegisterDTO register(RegisterDTO object) {
        User data = mapper.map(object, User.class);
        Optional<User> findEmail = userRepository.findUserByEmail(data.getEmail());
        if (findEmail.isPresent()) {
            throw new FoundException("Email address is already registered!");
        } else {
            Role roleMember = roleRepository.findByName("MEMBER").orElse(null);
            List<Role> roles = new ArrayList<>();
            roles.add(roleMember);

            data.setEmail(object.getEmail());
            data.setPassword(passwordEncoder.encode(object.getPassword()));
            data.setActive(false);

            userRepository.save(data);
            return mapper.map(data, RegisterDTO.class);
        }
    }

    @Override
    public LoginDTO login(LoginDTO object) {
        User user = userRepository.findUserByEmail(object.getEmail()).orElseThrow(
                () -> new NotFoundException("Email address is not registered!"));

        boolean isMatch = passwordEncoder.matches(object.getPassword(), user.getPassword());
        if (!isMatch) throw new RuntimeException("Password did not match!");
        if (!user.isActive()) throw new RuntimeException("Your account is not verified");

        return mapper.map(user, LoginDTO.class);
    }

    @Override
    public String createActivationToken(RegisterDTO object) {
        return Jwts.builder()
                .setSubject(object.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 900000))
                .signWith(Constant.SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String createAccessToken(LoginDTO object) {
        return Jwts.builder()
                .setSubject(object.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 345600000))
                .signWith(Constant.SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String createRefreshToken(LoginDTO object) {
        return Jwts.builder()
                .setSubject(object.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1209600000))
                .signWith(Constant.SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public void sendEmailAccountVerification(String to) {

    }
}
