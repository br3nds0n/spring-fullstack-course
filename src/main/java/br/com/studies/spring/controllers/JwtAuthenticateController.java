package br.com.studies.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.studies.spring.entities.User;
import br.com.studies.spring.repositories.UserRepository;

@RestController
public class JwtAuthenticateController {

    @Autowired
    private UserRepository repository; 
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<User> usuarios = new ArrayList<>();
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String createToken(@RequestBody User auth) {
        usuarios = repository.findAll();

        for (User user : usuarios) {
            if (user.getUsername().equals(auth.getUsername()) && passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
                return user.getUsername() + "OK";
            } else {
                return "erro";
            }
        }
        return null;
    }
}
