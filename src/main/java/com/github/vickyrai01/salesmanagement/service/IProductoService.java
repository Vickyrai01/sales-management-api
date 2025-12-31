package com.github.vickyrai01.salesmanagement.service;

import com.github.vickyrai01.salesmanagement.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> getAllProductos();
    ProductoDTO getProductoById(Long id);
    ProductoDTO saveProducto(ProductoDTO productoDTO);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);
    void deleteProducto(Long id);
}
