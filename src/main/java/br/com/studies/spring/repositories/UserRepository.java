package br.com.studies.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studies.spring.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    
}
