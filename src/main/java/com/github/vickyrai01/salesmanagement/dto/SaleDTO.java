package com.github.vickyrai01.salesmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {

    private Long id;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "State cannot be null")
    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotNull(message = "Total cannot be null")
    @PositiveOrZero(message = "Total cannot be negative")
    private Double total;

    @NotNull(message = "The sale must have a branch")
    private Long branchId;

    @NotNull(message = "The sale must have at least one item")
    private List<SaleItemDTO> saleItemDTOList;
}
