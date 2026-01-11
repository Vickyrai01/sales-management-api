package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.BranchDTO;
import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import com.github.vickyrai01.salesmanagement.exception.dto.ValidationErrorResponse;
import com.github.vickyrai01.salesmanagement.service.branch.IBranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branch")
@Tag(name = "Branches")
public class BranchController {

    private final IBranchService branchService;

    public BranchController(IBranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    @Operation(summary = "Get all branches", description = "Retrieve a list of all branches. Required roles: ADMIN, STOCK_MANAGER")
    public ResponseEntity<List<BranchDTO>> getAllBranches(){
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get branch by ID", description = "Retrieve a specific branch by its ID. Required roles: ADMIN, STOCK_MANAGER")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Branch found"),
        @ApiResponse(responseCode = "404", description = "Branch not found", 
            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id){
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new branch", description = "Create a new branch. Required role: ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Branch created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation error", 
            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
    })
    public ResponseEntity<BranchDTO> saveBranch(@Valid @RequestBody BranchDTO branchDTO){
        BranchDTO branch = branchService.saveBranch(branchDTO);
        return ResponseEntity.created(URI.create("/api/branch/" + branch.getId())).body(branch);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a branch", description = "Update an existing branch by ID. Required role: ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Branch updated successfully"),
        @ApiResponse(responseCode = "404", description = "Branch not found", 
            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO){
        BranchDTO branch = branchService.updateBranch(id, branchDTO);
        return ResponseEntity.ok(branch);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a branch", description = "Delete a branch by ID. Required role: ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Branch deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Branch not found", 
            content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id){
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
