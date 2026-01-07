package com.programacion.avanzada.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "authors")
@Entity
public class Author {
  @Id
  @TableGenerator(
      name = "author_gen",
      table = "id_gen",
      pkColumnName = "gen_name",
      valueColumnName = "gen_value",
      pkColumnValue = "author_id",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(generator = "author_gen")
  private Long id;

  private String name;

  private Integer version;

  @Builder.Default
  @ToString.Exclude
  @ManyToMany(mappedBy = "authors")
  private List<Book> books = new ArrayList<>();
}
