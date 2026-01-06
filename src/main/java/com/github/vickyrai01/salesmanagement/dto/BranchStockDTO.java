package com.github.vickyrai01.salesmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchStockDTO {

    private Long id;

    @NotNull(message = "Product id cannot be null")
    private Long productId;

    @NotNull(message = "Branch id cannot be null")
    private Long branchId;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;
}
