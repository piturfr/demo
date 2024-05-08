package com.example.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Price;
import com.example.infrastructure.rest.dto.ProductPriceDTO;

/**
 * 
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

	/**
	 * @param price
	 * @return
	 */
	@Mapping(source = "price.product.id", target = "productId")
	@Mapping(source = "price.brand.id", target = "brandId")
	ProductPriceDTO toDTO(Price price);

}
