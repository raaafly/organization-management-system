package com.permisitelu.api.config;

import com.permisitelu.api.module.faculty.Faculty;
import com.permisitelu.api.module.faculty.FacultyRepository;
import com.permisitelu.api.module.major.Major;
import com.permisitelu.api.module.major.MajorRepository;
import com.permisitelu.api.module.role.Role;
import com.permisitelu.api.module.role.RoleRepository;
import com.permisitelu.api.module.user.User;
import com.permisitelu.api.module.user.UserRepository;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Role SA = new Role(null, "SUPER ADMIN");
            Role ADMIN = new Role(null, "ADMINISTRATOR");
            Role MODERATOR = new Role(null, "MODERATOR");
            Role MEMBER = new Role(null, "MEMBER");
            roleRepository.save(SA);
            roleRepository.save(ADMIN);
            roleRepository.save(MODERATOR);
            roleRepository.save(MEMBER);

            Faculty FTE = new Faculty("Teknik Elektro");
            Faculty FIF = new Faculty("Informatika");
            Faculty FRI = new Faculty("Rekayasa Industri");
            facultyRepository.save(FTE);
            facultyRepository.save(FIF);
            facultyRepository.save(FRI);

            Major TE = new Major("Teknik Elektro", FTE);
            Major TK = new Major("Teknik Komputer", FTE);
            Major TT = new Major("Teknik Telekomunikasi", FTE);
            Major TI = new Major("Teknik Industri", FRI);
            Major SI = new Major("Sistem Informasi", FRI);
            Major TL = new Major("Teknik Logistik", FRI);
            Major IF = new Major("Informatika", FIF);
            Major DS = new Major("Data Science", FIF);
            majorRepository.save(TE);
            majorRepository.save(TK);
            majorRepository.save(TT);
            majorRepository.save(TI);
            majorRepository.save(SI);
            majorRepository.save(TL);
            majorRepository.save(IF);
            majorRepository.save(DS);

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
