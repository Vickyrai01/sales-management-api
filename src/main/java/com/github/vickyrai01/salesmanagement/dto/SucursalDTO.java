package com.github.vickyrai01.salesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

}
