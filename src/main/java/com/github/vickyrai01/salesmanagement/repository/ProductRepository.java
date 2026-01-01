package com.github.vickyrai01.salesmanagement.repository;

import com.github.vickyrai01.salesmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
