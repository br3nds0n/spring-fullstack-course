package br.com.studies.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.studies.spring.entities.Users;
import br.com.studies.spring.repositories.UserRepository;
import br.com.studies.spring.security.JwtTokenUtil;
import br.com.studies.spring.security.JwtUserDetailsService;

@RestController
public class JwtAuthenticateController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUserDetailsService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private List<Users> usuarios = new ArrayList<>();
    private String token;
    private Long id;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Optional<Users> createToken(@RequestBody Users auth) {
        usuarios = repository.findAll();

        for (Users user : usuarios) {
            if (user.getUsername().equals(auth.getUsername())
                    && passwordEncoder.matches(auth.getPassword(), user.getPassword())) {

                final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(auth.getUsername());

                this.token = jwtTokenUtil.generateToken(userDetails);
                this.token = jwtTokenUtil.generateToken(userDetails);
                this.id = user.getId();
                Optional<Users> obj = null;
                obj = repository.findById(this.id);
                obj.orElseThrow().setToken(token);
                obj.orElseThrow().setPassword("");
                return obj;
            }
        }
        this.id = (long) 1;
        Optional<Users> obj = null;
        
        obj = repository.findById(this.id);
        obj.orElseThrow().setToken(null);
        obj.orElseThrow().setId(null);
        obj.orElseThrow().setEmail("Erro ao tentar logar!");
        obj.orElseThrow().setPassword("");
        obj.orElseThrow().setUsername("username ou password não conferem!!");

        return obj;
    }
}
