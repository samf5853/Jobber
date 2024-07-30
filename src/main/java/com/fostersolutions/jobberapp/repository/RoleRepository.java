package com.fostersolutions.jobberapp.repository;



import com.fostersolutions.jobberapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);
}
