package com.prog_avanzada.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineItem {

    private Long orderId;
    private Integer idx;
    private Integer quantity;
    private String bookIsbn;

}