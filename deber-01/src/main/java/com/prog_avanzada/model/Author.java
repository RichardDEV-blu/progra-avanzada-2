package com.prog_avanzada.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
public class Author {

    private Long id;
    private String name;
    private Integer version;

}