package br.com.studies.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.studies.spring.entities.Users;
import br.com.studies.spring.repositories.UserRepository;
import br.com.studies.spring.security.JwtUserDetailsService;

@RestController
public class JwtAuthenticateController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUserDetailsService jwtUserDetailService;

    private List<Users> usuarios = new ArrayList<>();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String createToken(@RequestBody Users auth) {
        usuarios = repository.findAll();

        for (Users user : usuarios) {
            if (user.getUsername().equals(auth.getUsername())
                    && passwordEncoder.matches(auth.getPassword(), user.getPassword())) {

                final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(auth.getUsername());

                return user.getUsername() + "OK";
            } else {
                return "erro";
            }
        }
        return null;
    }
}
