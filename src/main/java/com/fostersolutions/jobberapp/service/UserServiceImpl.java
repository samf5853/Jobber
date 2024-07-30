package com.fostersolutions.jobberapp.service;



import com.fostersolutions.jobberapp.model.Role;
import com.fostersolutions.jobberapp.model.User;
import com.fostersolutions.jobberapp.repository.RoleRepository;
import com.fostersolutions.jobberapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllEmployee() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstName(StringUtils.capitalize(user.getFirstName().toLowerCase()));
        user.setLastName(StringUtils.capitalize(user.getLastName().toLowerCase()));

        Set<Role> roles = new HashSet<>();
        if (user.getEmail().endsWith("@admin.com")){
            Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
            if (adminRole == null){
                throw new RuntimeException("Admin role not found");
            }
            roles.add(adminRole);
        }else {
            Role userRole = roleRepository.findRoleByName("ROLE_USER");
            if (userRole == null){
                throw new RuntimeException("User role not found");
            }
            roles.add(userRole);
        }
        user.setRoles(roles);

        repository.save(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        User user = null;
        if (optionalUser.isPresent())
            user = optionalUser.get();
        else
            throw new RuntimeException(
                    "Employee not found for id : " + id
            );
        return user;
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
