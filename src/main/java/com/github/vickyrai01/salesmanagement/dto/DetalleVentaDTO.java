package com.github.vickyrai01.salesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDTO {

    private Long id;
    private Integer cantidad;
    private Double precio;

    private Double total;

    private Long productoId;
    private Long ventaId;
}
