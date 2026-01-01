package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Override
    public List<SaleDTO> getAllSales() {
        return List.of();
    }

    @Override
    public SaleDTO getSaleById(Long id) {
        return null;
    }

    @Override
    public SaleDTO saveSale(SaleDTO saleDTO) {
        return null;
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        return null;
    }

    @Override
    public void deleteSale(Long id) {

    }
}
