package com.programacion.avanzada.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "authors")
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer version;

  @ToString.Exclude
  @ManyToMany(mappedBy = "authors")
  private List<Book> books = new ArrayList<>();
}
