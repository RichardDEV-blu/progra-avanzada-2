package com.prog_avanzada.services;

import com.prog_avanzada.model.Author;
import com.prog_avanzada.repository.IAuthorRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class AuthorRepository implements IAuthorRepository {

  private final DbClient dbClient;

  @Inject
  public AuthorRepository(DbClient dbClient) {
    this.dbClient = dbClient;
  }

  @Override
  public List<Author> findAll() {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM author")
            .execute();

    return rows.map(this::mapToAuthor).toList();
  }

  @Override
  public Optional<Author> findById(Long id) {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM author WHERE id = ?")
            .params(id)
            .execute();

    return rows.map(this::mapToAuthor).findFirst();
  }

  @Override
  public boolean save(Author entity) {
    long rowsAffected = this.dbClient.execute()
            .createInsert("INSERT INTO author (name, version) VALUES (?, ?)")
            .params(entity.getName(), entity.getVersion())
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean update(Author entity) {
    long rowsAffected = this.dbClient.execute()
            .createUpdate("UPDATE author SET name = ?, version = ? WHERE id = ?")
            .params(
                    entity.getName(),
                    entity.getVersion(),
                    entity.getId()
            )
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean deleteById(Long id) {
    long rowsAffected = this.dbClient.execute()
            .createDelete("DELETE FROM author WHERE id = ?")
            .params(id)
            .execute();

    return rowsAffected > 0;
  }

  private Author mapToAuthor(DbRow row) {
    return Author.builder()
            .id(row.column("id").as(Long.class).get())
            .name(row.column("name").as(String.class).get())
            .version(row.column("version").as(Integer.class).get())
            .build();
  }
}