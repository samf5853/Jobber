package com.fostersolutions.jobberapp.service;



import com.fostersolutions.jobberapp.model.User;
import com.fostersolutions.jobberapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllEmployee();
    void save(User user);
    User getById(Long id);
    void deleteById(long id);

}
