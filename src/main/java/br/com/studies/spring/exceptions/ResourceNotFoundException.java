package br.com.studies.spring.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(Object id){
    super("Resource NotFound with id = " + id);
  }
}
