package com.project.eCommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eCommerce.entities.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {

	List<Basket> findByUserIdAndProductId(Long userId, Long productId);

	List<Basket> findByUserId(Long userId);

	List<Basket> findByProductId(Long productId);

}
