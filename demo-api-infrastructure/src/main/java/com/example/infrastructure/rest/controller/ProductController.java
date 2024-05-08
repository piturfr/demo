package com.example.infrastructure.rest.controller;

import java.util.Date;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.port.ProductPort;
import com.example.domain.model.Price;
import com.example.infrastructure.persistence.mapper.PriceMapper;
import com.example.infrastructure.rest.dto.ProductPriceDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductPort productPort;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	public ProductController(ProductPort port) {
		this.productPort = port;
	}

	@GetMapping("/{productId}/{brandId}")
	@Transactional
	public ResponseEntity<ProductPriceDTO> getPriceByDateAndProductAndBrand(@PathVariable("productId") Long productId,
			@PathVariable("brandId") Long brandId,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date date) {

		logger.info("Service [getPriceByDateAndProductAndBrand] START - date: {}, productId: {}, brandId: {}", date,
				productId, brandId);

		Price price = productPort.getPriceByDateAndProductAndBrand(date, productId, brandId);
		if (price != null) {
			ProductPriceDTO response = Mappers.getMapper(PriceMapper.class).toDTO(price);
			logger.info("Service [getPriceByDateAndProductAndBrand] END - response: {}", response.toString());
			return ResponseEntity.ok(response);
		} else {
			logger.info("Service [getPriceByDateAndProductAndBrand] END - respuesta vacia");
			return ResponseEntity.noContent().build();
		}
	}

}
