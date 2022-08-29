package br.com.studies.spring.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.studies.spring.entities.User;
import br.com.studies.spring.services.UserService;

@RestController
@RequestMapping({ "/users" })
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User list = service.findById(id);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        user = service.create(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping(path = { "/{id}" })
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        user = service.update(user, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
