package com.fostersolutions.jobberapp.controller;


import com.fostersolutions.jobberapp.model.Role;
import com.fostersolutions.jobberapp.model.User;
import com.fostersolutions.jobberapp.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    //    @Autowired
//    private PasswordEncoder encoder;
    @GetMapping
    public void home(HttpServletResponse response) throws IOException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")){
            response.sendRedirect("/login");
        }
        else {
            for (GrantedAuthority authority : authentication.getAuthorities()){
                if (authority.getAuthority().equals("ROLE_ADMIN")){
                    response.sendRedirect("/admin");
                    return;
                } else if (authority.getAuthority().equals("ROLE_USER")) {
                    response.sendRedirect("/user/profile");
                    return;
                }
            }
            response.sendRedirect("/register");
        }
    }

    @GetMapping("admin")
    public String viewHomePage(Model model) {
        List<User> users = userService.getAllEmployee();

        model.addAttribute("userList", users);

        return "admin";
    }

    @GetMapping("addnew")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newuser";
    }

    @GetMapping("register")
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute("user") User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin";

    }

    @GetMapping("updateform/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("logout")
    public String logout(){
        return "logout";
    }
}
