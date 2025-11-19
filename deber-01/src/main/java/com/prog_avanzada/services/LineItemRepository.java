package com.prog_avanzada.services;

import com.prog_avanzada.model.LineItem;
import com.prog_avanzada.repository.ILineItemRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class LineItemRepository implements ILineItemRepository {

  private final DbClient dbClient;

  @Inject
  public LineItemRepository(DbClient dbClient) {
    this.dbClient = dbClient;
  }

  @Override
  public List<LineItem> findAll() {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM lineitem")
            .execute();

    return rows.map(this::mapToLineItem).toList();
  }

  @Override
  public Optional<LineItem> findById(Long orderId, Integer idx) {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM lineitem WHERE order_id = ? AND idx = ?")
            .params(orderId, idx)
            .execute();

    return rows.map(this::mapToLineItem).findFirst();
  }

  @Override
  public boolean save(LineItem entity) {
    long rowsAffected = this.dbClient.execute()
            .createInsert("INSERT INTO lineitem (order_id, idx, quantity, book_isbn) VALUES (?, ?, ?, ?)")
            .params(
                    entity.getOrderId(),
                    entity.getIdx(),
                    entity.getQuantity(),
                    entity.getBookIsbn()
            )
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean update(LineItem entity) {
    long rowsAffected = this.dbClient.execute()
            .createUpdate("UPDATE lineitem SET quantity = ?, book_isbn = ? WHERE order_id = ? AND idx = ?")
            .params(
                    entity.getQuantity(),
                    entity.getBookIsbn(),
                    entity.getOrderId(),
                    entity.getIdx()
            )
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean deleteById(Long orderId, Integer idx) {
    long rowsAffected = this.dbClient.execute()
            .createDelete("DELETE FROM lineitem WHERE order_id = ? AND idx = ?")
            .params(orderId, idx)
            .execute();

    return rowsAffected > 0;
  }

  private LineItem mapToLineItem(DbRow row) {
    return LineItem.builder()
            .orderId(row.column("order_id").as(Long.class).get())
            .idx(row.column("idx").as(Integer.class).get())
            .quantity(row.column("quantity").as(Integer.class).get())
            .bookIsbn(row.column("book_isbn").as(String.class).get())
            .build();
  }
}