package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Override
    public List<ProductoDTO> getAllProductos() {
        return List.of();
    }

    @Override
    public ProductoDTO getProductoById(Long id) {
        return null;
    }

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        return null;
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        return null;
    }

    @Override
    public void deleteProducto(Long id) {

    }
}
