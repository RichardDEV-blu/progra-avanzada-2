package com.prog_avanzada.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {

    private Long id;
    private LocalDateTime deliveredOn;
    private LocalDateTime placedOn;
    private Integer status;
    private Integer total;
    private Long customerId;

}
