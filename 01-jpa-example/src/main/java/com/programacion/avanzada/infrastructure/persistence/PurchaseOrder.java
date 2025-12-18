package com.programacion.avanzada.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumeratedValue;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

  enum Status {
    PLACED,
    DELIVERED,
    CANCELED,
    ONGOING,
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "delivered_on")
  private LocalDateTime deliveredOn;

  @Column(name = "placed_on")
  private LocalDateTime placedOn;

  @Enumerated(EnumType.ORDINAL)
  private Status status;

  private Integer total;

  @ToString.Exclude
  @OneToMany(mappedBy = "purchaseOrder")
  private List<LineItem> lineItems = new ArrayList<>();

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
