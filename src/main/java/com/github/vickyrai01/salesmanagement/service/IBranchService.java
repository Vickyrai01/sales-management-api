package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;

import java.util.List;

public interface IBranchService {

    List<BranchDTO> getAllBranches();
    BranchDTO getBranchById(Long id);
    BranchDTO saveBranch(BranchDTO branchDTO);
    BranchDTO updateBranch(Long id, BranchDTO branchDTO);
    void deleteBranch(Long id);
}
