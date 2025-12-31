package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.SucursalDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursarService{

    @Override
    public List<SucursalDTO> getAllSucursales() {
        return List.of();
    }

    @Override
    public SucursalDTO getSucursalById(Long id) {
        return null;
    }

    @Override
    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO) {
        return null;
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO) {
        return null;
    }

    @Override
    public void deleteSucursal(Long id) {

    }
}
