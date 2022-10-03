package com.permisitelu.api.config;

import com.permisitelu.api.module.Department.Department;
import com.permisitelu.api.module.Department.DepartmentRepository;
import com.permisitelu.api.module.Faculty.Faculty;
import com.permisitelu.api.module.Faculty.FacultyRepository;
import com.permisitelu.api.module.Major.Major;
import com.permisitelu.api.module.Major.MajorRepository;
import com.permisitelu.api.module.MembershipType.MembershipType;
import com.permisitelu.api.module.MembershipType.MembershipTypeRepository;
import com.permisitelu.api.module.Position.Position;
import com.permisitelu.api.module.Position.PositionRepository;
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
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AppConfiguration {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final FacultyRepository facultyRepository;
    private final MajorRepository majorRepository;
    private final MembershipTypeRepository membershipTypeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            membershipTypeRepository.save(new MembershipType(null, "Alumni", "Alumni Keseluruhan Anggota PERMISI.TEL-U"));
            membershipTypeRepository.save(new MembershipType(null, "Anggota Tetap", "Anggota Kepengurusan PERMISI.TEL-U"));
            membershipTypeRepository.save(new MembershipType(null, "Anggota Tidak Tetap", "Anggota PERMISI.TEL-U tetapi tidak ada dalam kepengurusan"));

            for (String role : DataFaker.roleFaker) {
                roleRepository.save(new Role(null, role));
            }

            for (String faculty : DataFaker.facultyFaker) {
                facultyRepository.save(new Faculty(faculty));
            }

            for (String department : DataFaker.departmentFaker) {
                departmentRepository.save(new Department(department));
            }

            Faculty FTE = facultyRepository.findById(1L).orElse(null);
            Faculty FRI = facultyRepository.findById(2L).orElse(null);
            Faculty FIF = facultyRepository.findById(3L).orElse(null);
            Faculty FEB = facultyRepository.findById(4L).orElse(null);
            Faculty FKB = facultyRepository.findById(5L).orElse(null);
            Faculty FIK = facultyRepository.findById(6L).orElse(null);
            Faculty FIT = facultyRepository.findById(7L).orElse(null);

            for (String major : DataFaker.majorFTE) {
                majorRepository.save(new Major(major, FTE));
            }

            for (String major : DataFaker.majorFRI) {
                majorRepository.save(new Major(major, FRI));
            }

            for (String major : DataFaker.majorFIF) {
                majorRepository.save(new Major(major, FIF));
            }

            for (String major : DataFaker.majorFEB) {
                majorRepository.save(new Major(major, FEB));
            }

            for (String major : DataFaker.majorFKB) {
                majorRepository.save(new Major(major, FKB));
            }

            for (String major : DataFaker.majorFIK) {
                majorRepository.save(new Major(major, FIK));
            }

            for (String major : DataFaker.majorFIT) {
                majorRepository.save(new Major(major, FIT));
            }

            for (String position : DataFaker.positions) {
                positionRepository.save(new Position(position));
            }
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditConfiguration();
    }
}
