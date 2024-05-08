package com.example.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
