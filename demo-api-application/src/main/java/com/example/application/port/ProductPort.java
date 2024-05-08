package com.example.application.port;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.domain.model.Price;

@Service
public interface ProductPort {

	/**
	 * @param date
	 * @param productId
	 * @param brandId
	 * @return
	 */
	Price getPriceByDateAndProductAndBrand(Date date, Long productId, Long brandId);

}
