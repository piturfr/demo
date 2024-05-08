/**
 * 
 */
package com.example.infrastructure.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.application.port.ProductPort;
import com.example.domain.model.Brand;
import com.example.domain.model.Price;
import com.example.domain.model.Product;

/**
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductPort productPort;

	/**
	 * Test method for
	 * {@link com.example.infrastructure.rest.controller.ProductController#getPriceByDateAndProductAndBrand(java.lang.Long, java.lang.Long, java.util.Date)}.
	 */
	@Test
	public void testGetPriceByDateAndProductAndBrand() throws Exception {

		Long productId = 35455L;
		Long brandId = 1L;
		String fecha = "2020-06-14T10:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
		Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		Price price = new Price();
		price.setCurrency("EUR");
		price.setPrice(new BigDecimal(20));
		price.setStartDate(Date.from(dateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant()));
		price.setEndDate(Date.from(dateTime.plusDays(1).atZone(ZoneId.systemDefault()).toInstant()));
		price.setPriceList(2);
		price.setPriority(1);
		Product product = new Product();
		product.setId(35455L);
		product.setName("Nombre");
		price.setProduct(product);
		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("Zara");
		price.setBrand(brand);

		when(productPort.getPriceByDateAndProductAndBrand(date, productId, brandId)).thenReturn(price);

		mockMvc.perform(get("/products/{productId}/{brandId}", productId, brandId).param("date", fecha)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1L));

		mockMvc.perform(get("/products/{productId}/{brandId}", 2L, 3L).param("date", "2024-06-14T10:00:00")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

}
