package com.github.vickyrai01.salesmanagement.service.branch;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Branch;
import com.github.vickyrai01.salesmanagement.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BranchService implements IBranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        log.info("Getting all branches");
        return branchRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        log.info("Getting branch by id: {}", id);
        return branchRepository.findById(id).map(Mapper::toDTO)
                .orElseThrow(() ->  new NotFoundException("Branch not found"));
    }

    @Override
    public BranchDTO saveBranch(BranchDTO branchDTO) {

        log.info("Saving branch with name: {}", branchDTO.getName());
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

        var branch = branchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Branch not found"));

        branch.setName(branchDTO.getName());
        branch.setDirection(branchDTO.getDirection());
        branch.setTelephone(branchDTO.getTelephone());

        log.info("Updating branch with id: {}", branchDTO.getId());
        return Mapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public void deleteBranch(Long id) {
        if (!branchRepository.existsById(id)) throw new NotFoundException("Branch not found");

        log.info("Deleting branch by id: {}", id);
        branchRepository.deleteById(id);
    }
}