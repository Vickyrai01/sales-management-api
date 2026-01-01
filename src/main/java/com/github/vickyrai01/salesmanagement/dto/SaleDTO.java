package com.github.vickyrai01.salesmanagement.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {

    private Long id;
    private LocalDate date;
    private String state;
    private Double total;

    private Long branchId;

    private List<SaleItemDTO> saleItemDTOList;
}
