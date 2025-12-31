package com.github.vickyrai01.salesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;

    private Long sucursalId;

    private List<DetalleVentaDTO> detalleVentaDTOList;
}
