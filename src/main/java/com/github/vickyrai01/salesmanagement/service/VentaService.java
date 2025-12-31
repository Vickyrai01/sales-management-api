package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.VentaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {


    @Override
    public List<VentaDTO> getAllVentas() {
        return List.of();
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        return null;
    }

    @Override
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        return null;
    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventaDTO) {
        return null;
    }

    @Override
    public void deleteVenta(Long id) {

    }
}
