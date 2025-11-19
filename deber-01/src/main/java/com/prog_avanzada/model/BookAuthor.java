package com.prog_avanzada.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class BookAuthor {

    private String bookIsbn;
    private Long authorId;

}