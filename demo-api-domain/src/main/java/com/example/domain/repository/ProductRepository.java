package com.example.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
