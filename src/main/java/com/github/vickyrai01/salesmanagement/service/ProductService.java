package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.ProductDTO;
import com.github.vickyrai01.salesmanagement.mapper.Mapper;
import com.github.vickyrai01.salesmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
