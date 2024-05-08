package com.example.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

	/**
	 * @param productId
	 * @param brandId
	 * @param date
	 * @return
	 */
	Optional<List<Price>> findByIdProductBrandAndDate(Long productId, Long brandId, Date date);

}
