package br.com.studies.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studies.spring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
