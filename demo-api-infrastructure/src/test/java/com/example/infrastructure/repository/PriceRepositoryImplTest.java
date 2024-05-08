/**
 * 
 */
package com.example.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.domain.model.Price;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PriceRepositoryImplTest {

	@PersistenceContext
	private EntityManager entityManager;

	private PriceRepositoryImpl priceRepository;

	@BeforeEach
	void setUp() {
		priceRepository = new PriceRepositoryImpl(entityManager);
	}

	/**
	 * Test method for
	 * {@link com.example.infrastructure.repository.PriceRepositoryImpl#findByIdProductBrandAndDate(java.lang.Long, java.lang.Long, java.util.Date)}.
	 */
	@Test
	void testFindByIdProductBrandAndDate() {

		Long productId = 35455L;
		Long brandId = 1L;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T10:00:00", formatter);
		Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		Optional<List<Price>> result = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);
		assertEquals(1, result.orElse(null).size());
		assertEquals(new BigDecimal(35.50).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(0).getPrice());

		dateTime = LocalDateTime.parse("2020-06-14T16:00:00", formatter);
		date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		result = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);
		assertEquals(2, result.orElse(null).size());
		assertEquals(new BigDecimal(35.50).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(0).getPrice());
		assertEquals(new BigDecimal(25.45).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(1).getPrice());

		dateTime = LocalDateTime.parse("2020-06-14T21:00:00", formatter);
		date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		result = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);
		assertEquals(1, result.orElse(null).size());
		assertEquals(new BigDecimal(35.50).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(0).getPrice());

		dateTime = LocalDateTime.parse("2020-06-15T10:00:00", formatter);
		date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		result = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);
		assertEquals(2, result.orElse(null).size());
		assertEquals(new BigDecimal(35.50).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(0).getPrice());
		assertEquals(new BigDecimal(30.50).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(1).getPrice());

		dateTime = LocalDateTime.parse("2020-06-16T21:00:00", formatter);
		date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		result = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);
		assertEquals(2, result.orElse(null).size());
		assertEquals(new BigDecimal(35.50).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(0).getPrice());
		assertEquals(new BigDecimal(38.95).setScale(2, RoundingMode.HALF_DOWN), result.orElse(null).get(1).getPrice());

		dateTime = LocalDateTime.parse("2024-06-16T21:00:00", formatter);
		date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		result = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);
		assertEquals(0, result.orElse(null).size());

	}

}
