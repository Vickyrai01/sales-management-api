package com.github.vickyrai01.salesmanagement.service.category;

import com.github.vickyrai01.salesmanagement.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long id);

}
