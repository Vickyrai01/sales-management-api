package com.github.vickyrai01.salesmanagement.service.sale;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import com.github.vickyrai01.salesmanagement.dto.SaleItemDTO;
import com.github.vickyrai01.salesmanagement.exception.BadRequestException;
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
import com.github.vickyrai01.salesmanagement.service.branchStock.StockManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SaleService implements ISaleService {

    private final SaleRepository saleRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final SaleCalculator saleCalculator;
    private final SaleStateManager saleStateManager;
    private final StockManager stockManager;

    public SaleService(SaleRepository saleRepository, BranchRepository branchRepository, ProductRepository productRepository, SaleCalculator saleCalculator, SaleStateManager saleStateManager, StockManager stockManager) {
        this.saleRepository = saleRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.saleCalculator = saleCalculator;
        this.saleStateManager = saleStateManager;
        this.stockManager = stockManager;
    }

    @Override
    public List<SaleDTO> getAllSales() {
        log.info("Getting all sales");
        return saleRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SaleDTO getSaleById(Long id) {
        log.info("Getting sale by id: {}", id);
        return saleRepository.findById(id).map(Mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Sale not found"));
    }

    @Override
    public SaleDTO saveSale(SaleDTO saleDTO) {

        if(saleDTO.getSaleItemDTOList() == null) throw new BadRequestException("The list must contain at least one item");

        Branch branch = branchRepository.findById(saleDTO.getBranchId()).orElseThrow(() -> new NotFoundException("Branch not found"));
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

        log.info("Saving sale with id: {}", sale.getId());
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
        log.info("Updating sale with id: {}", saleDTO.getId());
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) throw new NotFoundException("Sale not found");

        log.info("Deleting sale by id: {}", id);
        saleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public SaleDTO confirmSale(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        stockManager.validateAndDecreaseStock(sale.getBranch().getId(), sale.getSaleItemList());
        sale = saleStateManager.confirmSale(sale);
        log.info("Sale with id {} confirmed", sale.getId());
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    @Transactional
    public SaleDTO paySale(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        sale = saleStateManager.paySale(sale);
        log.info("Sale with id {} paid", sale.getId());
        return Mapper.toDTO(saleRepository.save(sale));
    }

    @Override
    public SaleDTO cancelSale(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        sale = saleStateManager.cancelSale(sale);
        log.info("Sale with id {} canceled", sale.getId());
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
