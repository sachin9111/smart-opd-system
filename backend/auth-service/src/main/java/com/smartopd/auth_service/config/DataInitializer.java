package com.smartopd.auth_service.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartopd.auth_service.entity.Role;
import com.smartopd.auth_service.entity.enums.RoleCode;
import com.smartopd.auth_service.repository.RoleRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

	
    private final RoleRepository roleRepository;

    @Bean
    public CommandLineRunner initializeRoles() {

        return args -> {

            createRole(RoleCode.ROLE_ADMIN, "System Administrator");
            createRole(RoleCode.ROLE_DOCTOR, "Doctor");
            createRole(RoleCode.ROLE_PATIENT, "Patient");
            createRole(RoleCode.ROLE_RECEPTIONIST, "Receptionist");

        };

    }

    private void createRole(RoleCode roleCode, String description) {

        if (!roleRepository.existsByCode(roleCode)) {

            Role role = Role.builder()
                    .code(roleCode)
                    .description(description)
                    .build();

            roleRepository.save(role);

            System.out.println(roleCode + " created.");

        }

    }

}
