package com.github.vickyrai01.salesmanagement.service.product;

import com.github.vickyrai01.salesmanagement.dto.ProductDTO;
import com.github.vickyrai01.salesmanagement.exception.NotFoundException;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.model.Category;
import com.github.vickyrai01.salesmanagement.model.Product;
import com.github.vickyrai01.salesmanagement.repository.CategoryRepository;
import com.github.vickyrai01.salesmanagement.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("Getting product by id: {}", id);
        return productRepository.findById(id).map(Mapper::toDTO).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {

        var product = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .categoryList(productDTO.getCategoryList().stream().map(this::toClass).collect(Collectors.toSet()))
                .build();

        log.info("Saving product with name: {}", productDTO.getName());
        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        log.info("Updating product with id: {}", product.getId());
        return Mapper.toDTO(productRepository.save(product));
    }
    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) throw new NotFoundException("Product not found");
        log.info("Deleting product by id: {}", id);
        productRepository.deleteById(id);
    }

    private Category toClass(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    }
}
