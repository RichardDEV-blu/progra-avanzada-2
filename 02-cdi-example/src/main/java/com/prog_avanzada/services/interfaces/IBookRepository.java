package com.prog_avanzada.services.interfaces;

import com.prog_avanzada.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {
  Optional<Book> findByIsbn(String isbn);

  List<Book> findAll();

  void updateBook(Book book);

  void insert(Book book);

  void delete(String isbn);
}
