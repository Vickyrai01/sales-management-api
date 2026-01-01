package com.github.vickyrai01.salesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemDTO {

    private Long id;
    private Integer quantity;
    private Double price;

    private Double total;

    private Long productId;
    private Long SaleId;
}
