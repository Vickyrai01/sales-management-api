package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import com.github.vickyrai01.salesmanagement.service.sale.ISaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/sale")
public class SaleController {

    private final ISaleService saleService;

    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales(){
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id){
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> saveSale(@Valid @RequestBody SaleDTO saleDTO){
        SaleDTO sale = saleService.saveSale(saleDTO);
        return ResponseEntity.created(URI.create("/api/sale/" + sale.getId())).body(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO){
        SaleDTO sale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<SaleDTO> confirmSale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.confirmSale(id));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<SaleDTO> paySale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.paySale(id));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<SaleDTO> cancelSale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.cancelSale(id));
    }
}
