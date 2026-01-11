package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.SaleDTO;
import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import com.github.vickyrai01.salesmanagement.exception.dto.ValidationErrorResponse;
import com.github.vickyrai01.salesmanagement.service.sale.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/sale")
@Tag(name = "Sales", description = "Operations for managing sales and state transitions")
public class SaleController {

    private final ISaleService saleService;

    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    @Operation(
            summary = "Get all sales",
            description = "Retrieves a list of all sales. Requires SELLER role."
    )
    public ResponseEntity<List<SaleDTO>> getAllSales(){
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get sale by ID",
            description = "Retrieves a specific sale by its ID. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale found"),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id){
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @PostMapping
    @Operation(
            summary = "Create new sale",
            description = "Creates a new sale. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sale created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Branch or Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<SaleDTO> saveSale(@Valid @RequestBody SaleDTO saleDTO){
        SaleDTO sale = saleService.saveSale(saleDTO);
        return ResponseEntity.created(URI.create("/api/sale/" + sale.getId())).body(sale);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update sale",
            description = "Updates an existing sale. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale updated successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO){
        SaleDTO sale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete sale",
            description = "Deletes a sale by ID. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sale deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/confirm")
    @Operation(
            summary = "Confirm sale",
            description = "Confirms a sale, transitioning it from PENDING to CONFIRMED state. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "409", description = "Invalid state transition, insufficient stock, or product not available in branch",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<SaleDTO> confirmSale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.confirmSale(id));
    }

    @PostMapping("/{id}/pay")
    @Operation(
            summary = "Pay sale",
            description = "Marks a sale as paid, transitioning it from CONFIRMED to PAID state. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale paid successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "409", description = "Invalid state transition",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<SaleDTO> paySale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.paySale(id));
    }

    @PostMapping("/{id}/cancel")
    @Operation(
            summary = "Cancel sale",
            description = "Cancels a sale, transitioning it to CANCELLED state. Requires SELLER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "409", description = "Invalid state transition",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<SaleDTO> cancelSale(@PathVariable Long id){
        return ResponseEntity.ok(saleService.cancelSale(id));
    }
}
