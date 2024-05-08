package com.example.infrastructure.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.model.Brand;
import com.example.domain.model.Price;
import com.example.domain.model.Product;
import com.example.domain.repository.PriceRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class PriceRepositoryImpl extends SimpleJpaRepository<Price, Long> implements PriceRepository {

	private final EntityManager entityManager;

	/**
	 * @param domainClass
	 * @param entityManager
	 */
	public PriceRepositoryImpl(EntityManager entityManager) {
		super(Price.class, entityManager);
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<Price>> findByIdProductBrandAndDate(Long productId, Long brandId, Date date) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Price> criteriaQuery = criteriaBuilder.createQuery(Price.class);
		Root<Price> priceRoot = criteriaQuery.from(Price.class);

		Join<Price, Product> productJoin = priceRoot.join("product");
		Join<Price, Brand> brandJoin = priceRoot.join("brand");

		Predicate productIdPredicate = criteriaBuilder.equal(productJoin.get("id"), productId);
		Predicate brandIdPredicate = criteriaBuilder.equal(brandJoin.get("id"), brandId);
		Predicate datePredicate = criteriaBuilder.between(criteriaBuilder.literal(date), priceRoot.get("startDate"),
				priceRoot.get("endDate"));

		criteriaQuery.select(priceRoot).where(productIdPredicate, brandIdPredicate, datePredicate);

		TypedQuery<Price> typedQuery = entityManager.createQuery(criteriaQuery);
		return Optional.ofNullable(typedQuery.getResultList());
	}

}
