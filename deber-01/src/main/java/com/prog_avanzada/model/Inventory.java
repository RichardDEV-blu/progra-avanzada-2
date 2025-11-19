package com.prog_avanzada.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    private String bookIsbn;
    private Integer sold;
    private Integer supplied;
    private Integer version;

}