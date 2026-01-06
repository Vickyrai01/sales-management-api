package com.github.vickyrai01.salesmanagement.service.branchStock;

import com.github.vickyrai01.salesmanagement.dto.BranchStockDTO;

import java.util.List;

public interface IBranchStockService {

    List<BranchStockDTO> getAllBranchStock();
    BranchStockDTO getBranchStockById(Long id);
    BranchStockDTO saveBranchStock(BranchStockDTO branchStockDTO);
    BranchStockDTO updateBranchStock(Long id, BranchStockDTO branchStockDTO);
    void deleteBranchStock(Long id);
}
