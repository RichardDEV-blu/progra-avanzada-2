package com.prog_avanzada.repository;

import com.prog_avanzada.model.BookAuthor;
import java.util.List;
import java.util.Optional;

public interface IBookAuthorRepository {

  List<BookAuthor> findAll();

  Optional<BookAuthor> findById(String bookIsbn, Long authorId);

  boolean save(BookAuthor entity);

  boolean update(BookAuthor entity);

  boolean deleteById(String bookIsbn, Long authorId);
}