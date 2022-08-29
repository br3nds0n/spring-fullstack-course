package br.com.studies.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.studies.spring.entities.User;
import br.com.studies.spring.exceptions.ResourceNotFoundException;
import br.com.studies.spring.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User user, Long id) {
        user.setId(id);
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
