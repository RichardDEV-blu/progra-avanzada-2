package com.programacion.avanzada;

import com.programacion.avanzada.domain.entity.Author;
import com.programacion.avanzada.domain.repository.IAuthorRepository;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class MainJpa01 {
  public static void main(String[] args) {
    try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
      IAuthorRepository authorRepo = container.select(IAuthorRepository.class).get();

      // Crear y guardar un autor
      Author author = Author.builder().name("Gabriel García Márquez").build();
      authorRepo.save(author);

      System.out.println("Autor guardado (objeto): " + author);

      // Recuperar desde la base por ID y mostrar
      authorRepo.findById(author.getId()).ifPresent(a -> System.out.println("Autor recuperado (desde DB): " + a));
    }
  }
}
