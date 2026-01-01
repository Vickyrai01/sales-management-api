package com.github.vickyrai01.salesmanagement.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private String category;

}
