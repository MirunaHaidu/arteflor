package com.demo.arteflor.config;

import com.demo.arteflor.model.user.Role;
import com.demo.arteflor.repository.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializationConfig {
    private final RoleRepository roleRepository;

    public DataInitializationConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    @Transactional
    public void initRoles() {
        // Verifică dacă există deja roluri în baza de date
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            adminRole.setRoleId(101);
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setRoleName("USER");
            userRole.setRoleId(102);
            roleRepository.save(userRole);
        }
    }
}
