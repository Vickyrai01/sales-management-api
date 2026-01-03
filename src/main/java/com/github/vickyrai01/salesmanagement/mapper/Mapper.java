package com.github.vickyrai01.salesmanagement.mapper;

import com.github.vickyrai01.salesmanagement.dto.*;
import com.github.vickyrai01.salesmanagement.model.*;

public class Mapper {


    public static ProductDTO toDTO(Product product){

        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .categoryList(product.getCategoryList().stream().map(Category::getId).toList())
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

    public static CategoryDTO toDTO(Category category){
        if (category == null) return null;

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
