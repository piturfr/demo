package com.example.application.adapter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.application.comparators.PricePriorityComparator;
import com.example.application.port.ProductPort;
import com.example.domain.model.Price;
import com.example.domain.repository.PriceRepository;

/**
 * 
 */
@Service
public class ProductAdapter implements ProductPort {

	private final PriceRepository priceRepository;

	public ProductAdapter(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price getPriceByDateAndProductAndBrand(Date date, Long productId, Long brandId) {
		Optional<List<Price>> optionalPrice = priceRepository.findByIdProductBrandAndDate(productId, brandId, date);

		if (optionalPrice.isPresent()) {
			List<Price> priceList = optionalPrice.get();
			if (Boolean.FALSE.equals(CollectionUtils.isEmpty(priceList))) {
				priceList.sort(new PricePriorityComparator());
				return priceList.get(0);
			}
		}

		return null;
	}

}
