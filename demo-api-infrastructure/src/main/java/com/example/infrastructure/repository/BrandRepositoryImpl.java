package com.example.infrastructure.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.model.Brand;
import com.example.domain.repository.BrandRepository;

import jakarta.persistence.EntityManager;

@Repository
public class BrandRepositoryImpl extends SimpleJpaRepository<Brand, Long> implements BrandRepository {


	/**
	 * @param domainClass
	 * @param entityManager
	 */
	public BrandRepositoryImpl(EntityManager entityManager) {
		super(Brand.class, entityManager);
	}

}
