package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import com.github.vickyrai01.salesmanagement.dto.SaleItemDTO;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Branch;
import com.github.vickyrai01.salesmanagement.model.Product;
import com.github.vickyrai01.salesmanagement.model.Sale;
import com.github.vickyrai01.salesmanagement.model.SaleItem;
import com.github.vickyrai01.salesmanagement.repository.BranchRepository;
import com.github.vickyrai01.salesmanagement.repository.ProductRepository;
import com.github.vickyrai01.salesmanagement.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService {

    private final SaleRepository saleRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, BranchRepository branchRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SaleDTO getSaleById(Long id) {
        return saleRepository.findById(id).map(Mapper::toDTO).orElseThrow(() -> new NotFoundException("Sale not found"));
    }

    @Override
    public SaleDTO saveSale(SaleDTO saleDTO) {

        if(!branchRepository.existsById(saleDTO.getBranchId())) throw new NotFoundException("Branch not found");
        if(saleDTO.getSaleItemDTOList() == null) throw new RuntimeException("The list must contain at least one item");

        Branch branch = branchRepository.findById(saleDTO.getBranchId()).orElse(null);
        List<SaleItem> saleItemList = saleDTO.getSaleItemDTOList().stream().map(this::toClass).toList();

        var sale = Sale.builder()
                .id(saleDTO.getId())
                .date(saleDTO.getDate())
                .state(saleDTO.getState())
                .total(saleDTO.getTotal())
                .branch(branch)
                .saleItemList(saleItemList)
                .build();

        saleItemList.forEach(item -> item.setSale(sale));
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {

        var sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));

        if(saleDTO.getDate() != null) sale.setDate(saleDTO.getDate());
        if(saleDTO.getState() != null) sale.setState(saleDTO.getState());
        if(saleDTO.getTotal() != null) sale.setTotal(saleDTO.getTotal());
        if (saleDTO.getBranchId() != null){
            Branch branch = branchRepository.findById(saleDTO.getBranchId()).orElseThrow(() -> new NotFoundException("branch not found"));
            sale.setBranch(branch);
        }
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) throw new NotFoundException("Sale not found");
        saleRepository.deleteById(id);
    }

    private SaleItem toClass(SaleItemDTO saleItemDTO) {

        if(!productRepository.existsById(saleItemDTO.getProductId())) throw new NotFoundException("Product not found");

        Product product = productRepository.findById(saleItemDTO.getProductId()).orElse(null);

        return SaleItem.builder()
                .id(saleItemDTO.getId())
                .quantity(saleItemDTO.getQuantity())
                .price(saleItemDTO.getPrice())
                .total(saleItemDTO.getTotal())
                .product(product)
                .build();

    }

}
