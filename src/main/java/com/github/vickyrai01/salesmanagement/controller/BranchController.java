package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;
import com.github.vickyrai01.salesmanagement.service.IBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    private final IBranchService branchService;

    public BranchController(IBranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches(){
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id){
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PostMapping
    public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO){
        BranchDTO branch = branchService.saveBranch(branchDTO);
        return ResponseEntity.created(URI.create("/api/branch/" + branch.getId())).body(branch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO){
        BranchDTO branch = branchService.updateBranch(id, branchDTO);
        return ResponseEntity.ok(branch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id){
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
