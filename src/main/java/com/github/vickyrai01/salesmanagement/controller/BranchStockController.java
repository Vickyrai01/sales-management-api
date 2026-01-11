package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.BranchStockDTO;
import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import com.github.vickyrai01.salesmanagement.exception.dto.ValidationErrorResponse;
import com.github.vickyrai01.salesmanagement.model.BranchStock;
import com.github.vickyrai01.salesmanagement.service.branchStock.BranchStockService;
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
@RequestMapping("/api/branch-stock")
@Tag(name = "Branch Stock", description = "Operations for managing stock per branch (product x branch)")
public class BranchStockController {

    private final BranchStockService branchStockService;

    public BranchStockController(BranchStockService branchStockService) {
        this.branchStockService = branchStockService;
    }

    @GetMapping
    @Operation(
            summary = "Get all branch stock",
            description = "Retrieves a list of all branch stock records. Requires STOCK_MANAGER role."
    )
    public ResponseEntity<List<BranchStockDTO>> getAllBranchStock(){
        return ResponseEntity.ok(branchStockService.getAllBranchStock());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get branch stock by ID",
            description = "Retrieves a specific branch stock record by its ID. Requires STOCK_MANAGER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch stock found"),
            @ApiResponse(responseCode = "404", description = "Branch stock not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<BranchStockDTO> getBranchStockById(@PathVariable Long id){
        return ResponseEntity.ok(branchStockService.getBranchStockById(id));
    }

    @PostMapping()
    @Operation(
            summary = "Create new branch stock",
            description = "Creates a new branch stock record. Requires STOCK_MANAGER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Branch stock created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Branch or Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<BranchStockDTO> saveBranchStock(@Valid @RequestBody BranchStockDTO branchStockDTO){
        BranchStockDTO branchStock = branchStockService.saveBranchStock(branchStockDTO);
        return ResponseEntity.created(URI.create("/api/branch-stock/" + branchStock.getId())).body(branchStock);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update branch stock",
            description = "Updates an existing branch stock record. Requires STOCK_MANAGER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch stock updated successfully"),
            @ApiResponse(responseCode = "404", description = "Branch stock not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<BranchStockDTO> updateBranchStock(@PathVariable Long id, @RequestBody BranchStockDTO branchStockDTO){
        BranchStockDTO branchStock = branchStockService.updateBranchStock(id, branchStockDTO);
        return ResponseEntity.ok(branchStock);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete branch stock",
            description = "Deletes a branch stock record by ID. Requires STOCK_MANAGER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Branch stock deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Branch stock not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<Void> deleteBranchStock(@PathVariable Long id){
        branchStockService.deleteBranchStock(id);
        return ResponseEntity.noContent().build();
    }
}
