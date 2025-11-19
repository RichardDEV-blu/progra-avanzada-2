package com.prog_avanzada.services;

import com.prog_avanzada.model.BookAuthor;
import com.prog_avanzada.repository.IBookAuthorRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class BookAuthorRepository implements IBookAuthorRepository {

  private final DbClient dbClient;

  @Inject
  public BookAuthorRepository(DbClient dbClient) {
    this.dbClient = dbClient;
  }

  @Override
  public List<BookAuthor> findAll() {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM book_author")
            .execute();

    return rows.map(this::mapToBookAuthor).toList();
  }

  @Override
  public Optional<BookAuthor> findById(String bookIsbn, Long authorId) {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM book_author WHERE books_isbn = ? AND authors_id = ?")
            .params(bookIsbn, authorId)
            .execute();

    return rows.map(this::mapToBookAuthor).findFirst();
  }

  @Override
  public boolean save(BookAuthor entity) {
    long rowsAffected = this.dbClient.execute()
            .createInsert("INSERT INTO book_author (books_isbn, authors_id) VALUES (?, ?)")
            .params(entity.getBookIsbn(), entity.getAuthorId())
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean update(BookAuthor entity) {
    // ! En una tabla de union no hay nada que actualizar
    return false;
  }

  @Override
  public boolean deleteById(String bookIsbn, Long authorId) {
    long rowsAffected = this.dbClient.execute()
            .createDelete("DELETE FROM book_author WHERE books_isbn = ? AND authors_id = ?")
            .params(bookIsbn, authorId)
            .execute();

    return rowsAffected > 0;
  }

  private BookAuthor mapToBookAuthor(DbRow row) {
    return BookAuthor.builder()
            .bookIsbn(row.column("books_isbn").as(String.class).get())
            .authorId(row.column("authors_id").as(Long.class).get())
            .build();
  }
}