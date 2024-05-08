package com.example.infrastructure.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;

import jakarta.persistence.EntityManager;

@Repository
public class ProductRepositoryImpl extends SimpleJpaRepository<Product, Long> implements ProductRepository {


	/**
	 * @param domainClass
	 * @param entityManager
	 */
	public ProductRepositoryImpl(EntityManager entityManager) {
        super(Product.class, entityManager);
	}

}
