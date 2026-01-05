package com.github.vickyrai01.salesmanagement.service.calculator;

import com.github.vickyrai01.salesmanagement.dto.SaleItemDTO;
import com.github.vickyrai01.salesmanagement.model.Product;
import com.github.vickyrai01.salesmanagement.model.SaleItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleCalculator {

    public Double calculateSaleTotal(List<SaleItem> saleItemList){
        return saleItemList.stream().mapToDouble(SaleItem::getTotal).sum();
    }

    public Double calculateSaleItemTotal(Integer quantity, Product product){
        return quantity * product.getPrice();
    }
}
