package com.programacion.avanzada.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "books")
@Entity
public class Book {
  @Id
  private String isbn;
  private Integer version;
  private String title;
  private BigDecimal price;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(
    name = "book_authors",
    joinColumns = @JoinColumn(name = "book_isbn"),
    inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> authors = new ArrayList<>();

  @ToString.Exclude
  @OneToOne(mappedBy = "book")
  private Inventory inventory;
}