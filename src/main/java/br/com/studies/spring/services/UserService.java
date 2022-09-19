package br.com.studies.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.studies.spring.entities.Users;
import br.com.studies.spring.exceptions.ResourceNotFoundException;
import br.com.studies.spring.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String senhaComHash;

    @Autowired
    private UserRepository repository;

    public List<Users> findAll() {
        return repository.findAll();
    }

    public Users findById(Long id) {
        Optional<Users> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Users create(Users user) {
        this.senhaComHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(this.senhaComHash);

        return repository.save(user);
    }

    public Users update(Users user, Long id) {
        user.setId(id);
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
