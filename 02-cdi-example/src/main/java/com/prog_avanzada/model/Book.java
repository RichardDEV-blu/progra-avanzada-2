package com.prog_avanzada.model;

import lombok.*;


@Builder
@ToString
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private String isbn;
  private String title;
  private Double price;
  private Integer version;

//  public Book() {
//  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getVersion() {
    return version;
  }
}
