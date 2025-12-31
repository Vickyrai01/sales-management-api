package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.SucursalDTO;

import java.util.List;

public interface ISucursarService {

    List<SucursalDTO> getAllSucursales();
    SucursalDTO getSucursalById(Long id);
    SucursalDTO saveSucursal(SucursalDTO sucursalDTO);

    SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO);
    void deleteSucursal(Long id);
}
