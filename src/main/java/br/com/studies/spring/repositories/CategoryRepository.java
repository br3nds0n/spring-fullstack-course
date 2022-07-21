package br.com.studies.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studies.spring.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  
}
