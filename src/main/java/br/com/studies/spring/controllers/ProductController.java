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

import br.com.studies.spring.entities.Product;
import br.com.studies.spring.services.ProductService;

@RestController
@RequestMapping({"/produtos"})
public class ProductController {
  
  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<List<Product>> findAll(){
    List<Product> list = service.findAll();

    return ResponseEntity.ok().body(list);
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<?> findById(@PathVariable Long id){
    Product list = service.findById(id);

    return ResponseEntity.ok().body(list);
  } 

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product){
    product = service.create(product);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

    return ResponseEntity.created(uri).body(product);
  }

  @PutMapping(path = {"/{id}"})
  public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id){
    product = service.update(product, id);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = {"/{id}"})
  public ResponseEntity<?> delete(@PathVariable Long id){
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
