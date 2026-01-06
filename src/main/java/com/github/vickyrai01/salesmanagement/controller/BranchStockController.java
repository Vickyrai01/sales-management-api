package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.BranchStockDTO;
import com.github.vickyrai01.salesmanagement.model.BranchStock;
import com.github.vickyrai01.salesmanagement.service.branchStock.BranchStockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branch-stock")
public class BranchStockController {

    private final BranchStockService branchStockService;

    public BranchStockController(BranchStockService branchStockService) {
        this.branchStockService = branchStockService;
    }

    @GetMapping
    public ResponseEntity<List<BranchStockDTO>> getAllBranchStock(){
        return ResponseEntity.ok(branchStockService.getAllBranchStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchStockDTO> getBranchStockById(@PathVariable Long id){
        return ResponseEntity.ok(branchStockService.getBranchStockById(id));
    }

    @PostMapping()
    public ResponseEntity<BranchStockDTO> saveBranchStock(@Valid @RequestBody BranchStockDTO branchStockDTO){
        BranchStockDTO branchStock = branchStockService.saveBranchStock(branchStockDTO);
        return ResponseEntity.created(URI.create("/api/branch-stock/" + branchStock.getId())).body(branchStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchStockDTO> updateBranchStock(@PathVariable Long id, @RequestBody BranchStockDTO branchStockDTO){
        BranchStockDTO branchStock = branchStockService.updateBranchStock(id, branchStockDTO);
        return ResponseEntity.ok(branchStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranchStock(@PathVariable Long id){
        branchStockService.deleteBranchStock(id);
        return ResponseEntity.noContent().build();
    }
}
