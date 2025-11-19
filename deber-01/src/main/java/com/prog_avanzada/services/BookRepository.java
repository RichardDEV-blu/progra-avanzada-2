package com.prog_avanzada.services;

import com.prog_avanzada.model.Book;
import com.prog_avanzada.repository.IBookRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class BookRepository implements IBookRepository {

  private final DbClient dbClient;

  @Inject
  public BookRepository(DbClient dbClient) {
    this.dbClient = dbClient;
  }

  @Override
  public List<Book> findAll() {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM book")
            .execute();
    return rows.map(this::mapToBook).toList();
  }

  @Override
  public Optional<Book> findById(String isbn) {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM book WHERE isbn = ?")
            .params(isbn)
            .execute();
    return rows.map(this::mapToBook).findFirst();
  }

  @Override
  public boolean save(Book entity) {
    long rowsAffected = this.dbClient.execute()
            .createInsert("INSERT INTO book (isbn, title, price, version) VALUES (?, ?, ?, ?)")
            .params(
                    entity.getIsbn(),
                    entity.getTitle(),
                    entity.getPrice(),
                    entity.getVersion()
            )
            .execute();
    return rowsAffected > 0;
  }

  @Override
  public boolean update(Book entity) {
    long rowsAffected = this.dbClient.execute()
            .createUpdate("UPDATE book SET title = ?, price = ?, version = ? WHERE isbn = ?")
            .params(
                    entity.getTitle(),
                    entity.getPrice(),
                    entity.getVersion(),
                    entity.getIsbn()
            )
            .execute();
    return rowsAffected > 0;
  }

  @Override
  public boolean deleteById(String isbn) {
    long rowsAffected = this.dbClient.execute()
            .createDelete("DELETE FROM book WHERE isbn = ?")
            .params(isbn)
            .execute();
    return rowsAffected > 0;
  }

  private Book mapToBook(DbRow row) {
    return Book.builder()
            .isbn(row.column("isbn").as(String.class).get())
            .title(row.column("title").as(String.class).get())
            .price(row.column("price").as(Double.class).get())
            .version(row.column("version").as(Integer.class).get())
            .build();
  }
}