package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    @Override
    public List<BranchDTO> getAllBranches() {
        return List.of();
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        return null;
    }

    @Override
    public BranchDTO saveBranch(BranchDTO branchDTO) {
        return null;
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {
        return null;
    }

    @Override
    public void deleteBranch(Long id) {

    }
}
