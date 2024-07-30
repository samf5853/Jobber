package com.fostersolutions.jobberapp.config;

import com.fostersolutions.jobberapp.model.Permission;
import com.fostersolutions.jobberapp.model.Role;
import com.fostersolutions.jobberapp.model.User;
import com.fostersolutions.jobberapp.repository.PermissionRepository;
import com.fostersolutions.jobberapp.repository.RoleRepository;
import com.fostersolutions.jobberapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Permission readPermission = new Permission();
            readPermission.setName("READ_PRIVILEGES");
            permissionRepository.save(readPermission);

            Permission writePermission = new Permission();
            writePermission.setName("WRITE_PRIVILEGES");
            permissionRepository.save(writePermission);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            userRole.setPermissions(new HashSet<>(Set.of(readPermission)));
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
            roleRepository.save(adminRole);

            User user = new User();
            user.setFirstName("TEST_USER");
            user.setLastName("");
            user.setEmail("user@localhost.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(new HashSet<>(Set.of(userRole)));
            userRepository.save(user);

            User admin = new User();
            admin.setFirstName("ADMIN");
            admin.setLastName("");
            admin.setEmail("admin@localhost.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRoles(new HashSet<>(Set.of(adminRole)));
            userRepository.save(admin);
        };
    }

}
