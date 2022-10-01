package com.permisitelu.api.config;

import com.permisitelu.api.module.Department.Department;
import com.permisitelu.api.module.Department.DepartmentRepository;
import com.permisitelu.api.module.Faculty.Faculty;
import com.permisitelu.api.module.Faculty.FacultyRepository;
import com.permisitelu.api.module.Major.MajorRepository;
import com.permisitelu.api.module.Role.Role;
import com.permisitelu.api.module.Role.RoleRepository;
import com.permisitelu.api.module.User.User;
import com.permisitelu.api.module.User.UserRepository;
import com.permisitelu.api.utility.DataFaker;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditor")
public class AppConfiguration {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MajorRepository majorRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            for (String department : DataFaker.departmentFaker) {
                departmentRepository.save(new Department(department));
            }

            for (String role : DataFaker.roleFaker) {
                roleRepository.save(new Role(null, role));
            }

            for (String faculty : DataFaker.facultyFaker) {
                facultyRepository.save(new Faculty(faculty));
            }

            Role superAdmin = roleRepository.findById(Long.valueOf(1)).orElse(null);
            Role admin = roleRepository.findById(Long.valueOf(2)).orElse(null);
            Role mod = roleRepository.findById(Long.valueOf(3)).orElse(null);
            Role mem = roleRepository.findById(Long.valueOf(4)).orElse(null);
            List<Role> roles = new ArrayList<>();
            roles.add(superAdmin);
            roles.add(admin);
            roles.add(mod);
            roles.add(mem);
            User defaultUser = new User(
                    null,
                    "super.admin@permisitelu.com",
                    passwordEncoder.encode("permisitelu123/"),
                    true,
                    roles);
            userRepository.save(defaultUser);
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<String> auditor() {
        return new AuditableConfiguration();
    }
}
