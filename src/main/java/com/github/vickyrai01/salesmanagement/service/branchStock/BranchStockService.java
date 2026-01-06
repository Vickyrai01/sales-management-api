package com.github.vickyrai01.salesmanagement.service.branchStock;

import com.github.vickyrai01.salesmanagement.dto.BranchStockDTO;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Branch;
import com.github.vickyrai01.salesmanagement.model.BranchStock;
import com.github.vickyrai01.salesmanagement.model.Product;
import com.github.vickyrai01.salesmanagement.repository.BranchRepository;
import com.github.vickyrai01.salesmanagement.repository.BranchStockRepository;
import com.github.vickyrai01.salesmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchStockService implements IBranchStockService{

    private final BranchStockRepository branchStockRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public BranchStockService(BranchStockRepository branchStockRepository, BranchRepository branchRepository, ProductRepository productRepository) {
        this.branchStockRepository = branchStockRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<BranchStockDTO> getAllBranchStock() {
        return branchStockRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchStockDTO getBranchStockById(Long id) {
        return branchStockRepository.findById(id).map(Mapper::toDTO).orElseThrow(()-> new NotFoundException("Branch stock not found"));
    }

    @Override
    public BranchStockDTO saveBranchStock(BranchStockDTO branchStockDTO) {

        Branch branch = branchRepository.findById(branchStockDTO.getBranchId()).orElseThrow(()-> new NotFoundException("Branch not found"));
        Product product = productRepository.findById(branchStockDTO.getProductId()).orElseThrow(()-> new NotFoundException("Product not found"));

        var branchStock = BranchStock.builder()
                .id(branchStockDTO.getId())
                .branch(branch)
                .product(product)
                .quantity(branchStockDTO.getQuantity())
                .build();

        return Mapper.toDTO(branchStockRepository.save(branchStock));
    }

    @Override
    public BranchStockDTO updateBranchStock(Long id, BranchStockDTO branchStockDTO) {
        if (!branchStockRepository.existsById(id)) throw new NotFoundException("Branch stock not found");
        BranchStock branchStock = branchStockRepository.findById(id).orElseThrow(()-> new NotFoundException("Branch stock not found"));
        if(branchStockDTO.getQuantity() != null) branchStock.setQuantity(branchStockDTO.getQuantity());
        return Mapper.toDTO(branchStockRepository.save(branchStock));
    }

    @Override
    public void deleteBranchStock(Long id) {
        if (!branchStockRepository.existsById(id)) throw new NotFoundException("Branch stock not found");
        branchStockRepository.deleteById(id);
    }
}
