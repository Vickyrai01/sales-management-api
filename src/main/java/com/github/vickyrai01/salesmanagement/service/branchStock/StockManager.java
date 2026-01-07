package com.github.vickyrai01.salesmanagement.service.branchStock;

import com.github.vickyrai01.salesmanagement.exception.InsufficientStockException;
import com.github.vickyrai01.salesmanagement.exception.ProductNotAvailableInBranchException;
import com.github.vickyrai01.salesmanagement.model.BranchStock;
import com.github.vickyrai01.salesmanagement.model.SaleItem;
import com.github.vickyrai01.salesmanagement.repository.BranchStockRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockManager {

    private final BranchStockRepository branchStockRepository;

    public StockManager(BranchStockRepository branchStockRepository) {
        this.branchStockRepository = branchStockRepository;
    }

    public void validateAndDecreaseStock(Long branchId, List<SaleItem> items){

        for(SaleItem item : items){
            BranchStock branchStock = branchStockRepository
                    .findByBranch_IdAndProduct_Id(branchId, item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotAvailableInBranchException("The product " + item.getProduct().getName() + " is not available in branch " + branchId + "."));

            Integer quantity = branchStock.getQuantity();
            Integer required = item.getQuantity();

            if (quantity < required) throw new InsufficientStockException(
                    String.format(
                            "Insufficient stock for product %d in branch %d. Required %d, available %d.",
                            branchStock.getProduct().getId(),
                            branchStock.getBranch().getId(),
                            required,
                            quantity
                    )
            );

            branchStock.setQuantity(quantity - required);
            branchStockRepository.save(branchStock);
        }

    }

}
