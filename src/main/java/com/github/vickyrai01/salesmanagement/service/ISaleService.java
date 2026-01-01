package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;

import java.util.List;

public interface ISaleService {

    List<SaleDTO> getAllSales();
    SaleDTO getSaleById(Long id);
    SaleDTO saveSale(SaleDTO saleDTO);
    SaleDTO updateSale(Long id, SaleDTO saleDTO);
    void deleteSale(Long id);

}
