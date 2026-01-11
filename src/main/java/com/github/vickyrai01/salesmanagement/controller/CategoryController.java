package com.github.vickyrai01.salesmanagement.controller;

import com.github.vickyrai01.salesmanagement.dto.CategoryDTO;
import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import com.github.vickyrai01.salesmanagement.service.category.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Categories", description = "Operations for managing product categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieves a list of all categories. Requires ADMIN role."
    )
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get category by ID",
            description = "Retrieves a specific category by its ID. Requires ADMIN role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    @Operation(
            summary = "Create new category",
            description = "Creates a new category. Requires ADMIN role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "409", description = "Category already exists",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.created(URI.create("/api/category/" + category.getId())).body(category);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete category",
            description = "Deletes a category by ID. Requires ADMIN role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
