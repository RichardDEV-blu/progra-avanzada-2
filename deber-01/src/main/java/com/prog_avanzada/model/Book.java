package com.prog_avanzada.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book {

    private String isbn;
    private Double price;
    private String title;
    private Integer version;

}