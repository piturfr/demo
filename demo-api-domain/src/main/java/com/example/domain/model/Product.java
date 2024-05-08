package com.example.domain.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "product")
	private List<Price> prices;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the prices
	 */
	public List<Price> getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

}
