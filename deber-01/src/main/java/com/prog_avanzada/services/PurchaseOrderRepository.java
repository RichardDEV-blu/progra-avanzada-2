package com.prog_avanzada.services;

import com.prog_avanzada.model.PurchaseOrder;
import com.prog_avanzada.repository.IPurchaseOrderRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class PurchaseOrderRepository implements IPurchaseOrderRepository {

  private final DbClient dbClient;

  @Inject
  public PurchaseOrderRepository(DbClient dbClient) {
    this.dbClient = dbClient;
  }

  @Override
  public List<PurchaseOrder> findAll() {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM purchaseorder")
            .execute();

    return rows.map(this::mapToOrder).toList();
  }

  @Override
  public Optional<PurchaseOrder> findById(Long id) {
    Stream<DbRow> rows = this.dbClient.execute()
            .createQuery("SELECT * FROM purchaseorder WHERE id = ?")
            .params(id)
            .execute();

    return rows.map(this::mapToOrder).findFirst();
  }

  @Override
  public boolean save(PurchaseOrder entity) {
    Long rowsAffected = this.dbClient.execute()
            .createInsert("INSERT INTO purchaseorder (deliveredon, placedon, status, total, customer_id) VALUES (?, ?, ?, ?, ?)")
            .params(
                    entity.getDeliveredOn(),
                    entity.getPlacedOn(),
                    entity.getStatus(),
                    entity.getTotal(),
                    entity.getCustomerId()
            )
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean update(PurchaseOrder entity) {
    long rowsAffected = this.dbClient.execute()
            .createUpdate("UPDATE purchaseorder SET deliveredon = ?, placedon = ?, status = ?, total = ?, customer_id = ? WHERE id = ?")
            .params(
                    entity.getDeliveredOn(),
                    entity.getPlacedOn(),
                    entity.getStatus(),
                    entity.getTotal(),
                    entity.getCustomerId(),
                    entity.getId()
            )
            .execute();

    return rowsAffected > 0;
  }

  @Override
  public boolean deleteById(Long id) {
    long rowsAffected = this.dbClient.execute()
            .createDelete("DELETE FROM purchaseorder WHERE id = ?")
            .params(id)
            .execute();

    return rowsAffected > 0;
  }

  private PurchaseOrder mapToOrder(DbRow row) {
    return PurchaseOrder.builder()
            .id(row.column("id").as(Long.class).get())
            .deliveredOn(row.column("deliveredon").as(LocalDateTime.class).orElse(null))
            .placedOn(row.column("placedon").as(LocalDateTime.class).orElse(null))
            .status(row.column("status").as(Integer.class).get())
            .total(row.column("total").as(Integer.class).get())
            .customerId(row.column("customer_id").as(Long.class).get())
            .build();
  }
}