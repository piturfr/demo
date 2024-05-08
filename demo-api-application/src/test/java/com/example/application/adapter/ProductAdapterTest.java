/**
 * 
 */
package com.example.application.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.model.Price;
import com.example.domain.repository.PriceRepository;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
class ProductAdapterTest {

	@InjectMocks
	private ProductAdapter productAdapter;
	@Mock
	private PriceRepository priceRepositoryMock;

	@BeforeEach
	public void setUp() {
		priceRepositoryMock = mock(PriceRepository.class);
		productAdapter = new ProductAdapter(priceRepositoryMock);
	}

	/**
	 * Test method for
	 * {@link com.example.application.adapter.ProductAdapter#getPriceByDateAndProductAndBrand(java.util.Date, java.lang.Long, java.lang.Long)}.
	 */
	@Test
	void testGetPriceByDateAndProductAndBrand() {

		String fecha = "2020-06-14T10:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
		Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		Long productId = 1L;
		Long brandId = 1L;
		List<Price> priceList = new ArrayList<>();
		Price price = new Price();
		price.setId(1L);
		price.setCurrency("EUR");
		price.setPrice(new BigDecimal(20));
		price.setStartDate(Date.from(dateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant()));
		price.setEndDate(Date.from(dateTime.plusDays(1).atZone(ZoneId.systemDefault()).toInstant()));
		price.setPriceList(2);
		price.setPriority(2);
		priceList.add(price);
		Optional<List<Price>> optionalPrice = Optional.of(priceList);
		when(priceRepositoryMock.findByIdProductBrandAndDate(productId, brandId, date)).thenReturn(optionalPrice);
		Price result = productAdapter.getPriceByDateAndProductAndBrand(date, productId, brandId);
		assertEquals(price, result);

		Price price2 = new Price();
		price2.setId(2L);
		price2.setCurrency("EUR");
		price2.setPrice(new BigDecimal(15));
		price2.setStartDate(Date.from(dateTime.minusDays(2).atZone(ZoneId.systemDefault()).toInstant()));
		price2.setEndDate(Date.from(dateTime.plusDays(2).atZone(ZoneId.systemDefault()).toInstant()));
		price2.setPriceList(3);
		price2.setPriority(1);
		priceList.add(price2);
		optionalPrice = Optional.of(priceList);
		when(priceRepositoryMock.findByIdProductBrandAndDate(productId, brandId, date)).thenReturn(optionalPrice);
		result = productAdapter.getPriceByDateAndProductAndBrand(date, productId, brandId);
		assertEquals(price2, result);

		price2.setPriority(5);
		priceList.clear();
		priceList.add(price);
		priceList.add(price2);
		optionalPrice = Optional.of(priceList);
		when(priceRepositoryMock.findByIdProductBrandAndDate(productId, brandId, date)).thenReturn(optionalPrice);
		result = productAdapter.getPriceByDateAndProductAndBrand(date, productId, brandId);
		assertEquals(price, result);

		priceList = new ArrayList<>();
		optionalPrice = Optional.of(priceList);
		when(priceRepositoryMock.findByIdProductBrandAndDate(productId, brandId, date)).thenReturn(optionalPrice);
		result = productAdapter.getPriceByDateAndProductAndBrand(date, productId, brandId);
		assertEquals(null, result);

		optionalPrice = Optional.empty();
		when(priceRepositoryMock.findByIdProductBrandAndDate(productId, brandId, date)).thenReturn(optionalPrice);
		result = productAdapter.getPriceByDateAndProductAndBrand(date, productId, brandId);
		assertEquals(null, result);

		result = productAdapter.getPriceByDateAndProductAndBrand(date, 2L, 10L);
		assertEquals(null, result);
	}

}
