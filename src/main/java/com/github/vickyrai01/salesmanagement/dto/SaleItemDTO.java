package com.github.vickyrai01.salesmanagement.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleItemDTO {

    private Long id;
    private Integer quantity;
    private Double price;

    private Double total;

    private Long productId;
    private Long saleId;
}
