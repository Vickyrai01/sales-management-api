package com.github.vickyrai01.salesmanagement.service.category;

import com.github.vickyrai01.salesmanagement.dto.CategoryDTO;
import com.github.vickyrai01.salesmanagement.exception.AlreadyExistsException;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Category;
import com.github.vickyrai01.salesmanagement.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        log.info("Getting category by id: {}", id);
        return categoryRepository.findById(id).map(Mapper::toDTO).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

        String normalized = categoryDTO.getName().trim().toLowerCase();

        if(categoryRepository.existsByName(normalized)) throw new AlreadyExistsException("Category already exists");

        var category = Category.builder()
                .id(categoryDTO.getId())
                .name(normalized)
                .build();

        log.info("Saving category with name: {}", categoryDTO.getName());
        return Mapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) throw new NotFoundException("Category not found");

        log.info("Deleting category by id: {}", id);
        categoryRepository.deleteById(id);
    }
}
