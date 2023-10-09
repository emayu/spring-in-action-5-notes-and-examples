/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.security;

import dev.yaque.tacocloud.User;
import dev.yaque.tacocloud.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yaque
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(RegistrationController.class);
    
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping
    public String registerForm(){
        return "registration";
    }
    
    @PostMapping
    public String processRegistration(RegistrationForm form){
        log.info("recived info: " + form.toString());
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
    
}
