package com.fostersolutions.jobberapp.controller;

import com.fostersolutions.jobberapp.model.User;
import com.fostersolutions.jobberapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/user/profile")
public class UserController {

    @Autowired
    private UserRepository repository;


    @GetMapping
    public String addNewUser(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            model.addAttribute("username", userDetails.getUsername());
            User user = repository.findUserByEmail(userDetails.getUsername());
            model.addAttribute("firstname", user.getFirstName());
            return "userprofile";
        } catch (Exception e) {
            return e.getLocalizedMessage() + Arrays.toString(e.getStackTrace());

        }

    }

    @GetMapping(path = "/{id}")
    public User getUser1(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }


    @GetMapping("/{id}/name")
    public ResponseEntity<String> getUserName(@PathVariable(name = "id") Long item) {
        User user = repository.findById(item).get();
        String body = "<h1>" + user.getFirstName() + " " + user.getLastName() + "</h1>";

        return ResponseEntity.ok(body);
    }

}
