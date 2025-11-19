package com.prog_avanzada.services;

import com.prog_avanzada.model.Inventory;
import com.prog_avanzada.repository.IInventoryRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class InventoryRepository implements IInventoryRepository {

  private final DbClient dbClient;

  @Inject
  public InventoryRepository(DbClient dbClient) {
    this.dbClient = dbClient;
  }

  @Override
  public List<Inventory> findAll() {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM inventory")
            .execute();
    return rows.map(this::mapToInventory).toList();
  }

  @Override
  public Optional<Inventory> findById(String bookIsbn) {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM inventory WHERE book_isbn = ?")
            .params(bookIsbn)
            .execute();
    return rows.map(this::mapToInventory).findFirst();
  }

  @Override
  public boolean save(Inventory entity) {
    long rowsAffected = this.dbClient.execute()
            .createInsert("INSERT INTO inventory (book_isbn, sold, supplied, version) VALUES (?, ?, ?, ?)")
            .params(
              entity.getBookIsbn(),
              entity.getSold(),
              entity.getSupplied(),
              entity.getVersion()
            )
            .execute();
    return rowsAffected > 0;
  }

  @Override
  public boolean update(Inventory entity) {
    long rowsAffected = this.dbClient.execute()
            .createUpdate("UPDATE inventory SET sold = ?, supplied = ?, version = ? WHERE book_isbn = ?")
            .params(
              entity.getSold(),
              entity.getSupplied(),
              entity.getVersion(),
              entity.getBookIsbn()
            )
            .execute();
    return rowsAffected > 0;
  }

  @Override
  public boolean deleteById(String bookIsbn) {
    long rowsAffected = this.dbClient.execute()
            .createDelete("DELETE FROM inventory WHERE book_isbn = ?")
            .params(bookIsbn)
            .execute();
    return rowsAffected > 0;
  }

  private Inventory mapToInventory(DbRow row) {
    return Inventory.builder()
            .bookIsbn(row.column("book_isbn").as(String.class).get())
            .sold(row.column("sold").as(Integer.class).get())
            .supplied(row.column("supplied").as(Integer.class).get())
            .version(row.column("version").as(Integer.class).get())
            .build();
  }
}