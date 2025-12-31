package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> getAllVentas();
    VentaDTO getVentaById(Long id);
    VentaDTO saveVenta(VentaDTO ventaDTO);
    VentaDTO updateVenta(Long id, VentaDTO ventaDTO);
    void deleteVenta(Long id);

}
