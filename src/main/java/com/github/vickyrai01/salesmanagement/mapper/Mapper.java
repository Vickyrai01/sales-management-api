package com.github.vickyrai01.salesmanagement.mapper;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;
import com.github.vickyrai01.salesmanagement.dto.ProductDTO;
import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import com.github.vickyrai01.salesmanagement.dto.SaleItemDTO;
import com.github.vickyrai01.salesmanagement.model.Branch;
import com.github.vickyrai01.salesmanagement.model.Product;
import com.github.vickyrai01.salesmanagement.model.Sale;
import com.github.vickyrai01.salesmanagement.model.SaleItem;

public class Mapper {


    public static ProductDTO toDTO(Product product){

        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .build();
    }

    public static BranchDTO toDTO(Branch branch){
        if (branch == null) return null;

        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .direction(branch.getDirection())
                .telephone(branch.getTelephone())
                .build();
    }

    public static SaleItemDTO toDTO(SaleItem saleItem){
        if (saleItem == null) return null;

        return SaleItemDTO.builder()
                .id(saleItem.getId())
                .quantity(saleItem.getQuantity())
                .price(saleItem.getPrice())
                .total(saleItem.getTotal())
                .productId(saleItem.getProduct().getId())
                .saleId(saleItem.getSale().getId())
                .build();
    }

    //sale to DTO
    public static SaleDTO toDTO(Sale sale){
        if (sale == null) return null;

        return SaleDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .state(sale.getState())
                .total(sale.getTotal())
                .branchId(sale.getBranch().getId())
                .saleItemDTOList(sale.getSaleItemList().stream().map(Mapper::toDTO).toList())
                .build();
    }

}
