package com.github.vickyrai01.salesmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleItemDTO {

    private Long id;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;
    private Double price;
    private Double total;

    private Long saleId;

    @NotNull(message = "Product id cannot be null")
    @Positive(message = "Product id must be greater than 0")
    private Long productId;


}
