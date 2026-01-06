package com.github.vickyrai01.salesmanagement.service.sale;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import com.github.vickyrai01.salesmanagement.dto.SaleItemDTO;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Branch;
import com.github.vickyrai01.salesmanagement.model.Product;
import com.github.vickyrai01.salesmanagement.model.Sale;
import com.github.vickyrai01.salesmanagement.model.SaleItem;
import com.github.vickyrai01.salesmanagement.model.enums.SaleState;
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
    private final SaleCalculator saleCalculator;
    private final SaleStateManager saleStateManager;

    public SaleService(SaleRepository saleRepository, BranchRepository branchRepository, ProductRepository productRepository, SaleCalculator saleCalculator, SaleStateManager saleStateManager) {
        this.saleRepository = saleRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.saleCalculator = saleCalculator;
        this.saleStateManager = saleStateManager;
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
        Double total = saleCalculator.calculateSaleTotal(saleItemList);

        var sale = Sale.builder()
                .id(saleDTO.getId())
                .date(saleDTO.getDate())
                .state(SaleState.CREATED)
                .total(total)
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

    @Override
    public SaleDTO confirmSale(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        sale = saleStateManager.confirmSale(sale);
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public SaleDTO paySale(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        sale = saleStateManager.paySale(sale);
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public SaleDTO cancelSale(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        sale = saleStateManager.cancelSale(sale);
        return Mapper.toDTO(saleRepository.save(sale));
    }

    private SaleItem toClass(SaleItemDTO saleItemDTO) {

        Product product = productRepository.findById(saleItemDTO.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));
        Double total = saleCalculator.calculateSaleItemTotal(saleItemDTO.getQuantity(), product);

        return SaleItem.builder()
                .id(saleItemDTO.getId())
                .quantity(saleItemDTO.getQuantity())
                .price(product.getPrice())
                .total(total)
                .product(product)
                .build();

    }

}
