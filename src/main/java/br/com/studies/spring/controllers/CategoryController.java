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

import br.com.studies.spring.services.CategoryService;
import br.com.studies.spring.entities.Category;

@RestController
@RequestMapping({"/categories"})
public class CategoryController {
  
  @Autowired
  private CategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> findAll(){
    List<Category> list = service.findAll();

    return ResponseEntity.ok().body(list);
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<?> findById(@PathVariable Long id){
    Category list = service.findById(id);

    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<Category> create(@RequestBody Category category){
    category = service.create(category);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();

    return ResponseEntity.created(uri).body(category);
  }

  @PutMapping(path = {"/{id}"})
  public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id){
    category = service.update(category, id);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = {"/{id}"})
  public ResponseEntity<?> delete(@PathVariable Long id ){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
