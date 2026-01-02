package com.github.vickyrai01.salesmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Direction cannot be null")
    @NotNull(message = "Direction cannot be null")
    private String direction;

    @NotBlank(message = "Telephone cannot be null")
    @NotNull(message = "Telephone cannot be null")
    private String telephone;

}
