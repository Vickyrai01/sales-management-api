package com.github.vickyrai01.salesmanagement.service.branch;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Branch;
import com.github.vickyrai01.salesmanagement.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        return branchRepository.findById(id).map(Mapper::toDTO).orElseThrow(() -> new NotFoundException("Branch not found"));
    }

    @Override
    public BranchDTO saveBranch(BranchDTO branchDTO) {

        var branch = Branch.builder()
                .id(branchDTO.getId())
                .name(branchDTO.getName())
                .direction(branchDTO.getDirection())
                .telephone(branchDTO.getTelephone())
                .build();

        return Mapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {

        var branch = branchRepository.findById(id).orElseThrow(() -> new NotFoundException("Branch not found"));

        branch.setName(branchDTO.getName());
        branch.setDirection(branchDTO.getDirection());
        branch.setTelephone(branchDTO.getTelephone());

        return Mapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public void deleteBranch(Long id) {
        if (!branchRepository.existsById(id)) throw new NotFoundException("Branch not found");
        branchRepository.deleteById(id);
    }
}
