package com.github.vickyrai01.salesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private Long id;
    private String name;
    private String direction;
    private String telephone;

}
