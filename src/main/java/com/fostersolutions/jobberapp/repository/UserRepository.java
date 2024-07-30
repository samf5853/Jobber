package com.fostersolutions.jobberapp.repository;



import com.fostersolutions.jobberapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

}
