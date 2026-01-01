package com.github.vickyrai01.salesmanagement.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDTO {

    private Long id;
    private String name;
    private String direction;
    private String telephone;

}
