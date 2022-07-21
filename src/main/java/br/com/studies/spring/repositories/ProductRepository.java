package br.com.studies.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studies.spring.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
  
}
