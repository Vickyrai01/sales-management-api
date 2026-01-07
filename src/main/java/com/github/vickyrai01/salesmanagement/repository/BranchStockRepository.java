package com.github.vickyrai01.salesmanagement.repository;

import com.github.vickyrai01.salesmanagement.model.BranchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchStockRepository extends JpaRepository<BranchStock, Long> {

    Optional<BranchStock> findByBranch_IdAndProduct_Id(Long branchId, Long productId);
}
