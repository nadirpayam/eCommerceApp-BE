package com.project.eCommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.eCommerce.entities.Basket;
import com.project.eCommerce.entities.Product;
import com.project.eCommerce.entities.User;
import com.project.eCommerce.repositories.BasketRepository;
import com.project.eCommerce.reuqests.BasketCreateRequest;
import com.project.eCommerce.reuqests.BasketUpdateRequest;

@Service
public class BasketService {

	private BasketRepository basketRepository;
	private UserService userService;
	private ProductService productService;

	public BasketService(BasketRepository basketRepository, UserService userService, ProductService productService) {
		this.basketRepository = basketRepository;
		this.userService = userService;
		this.productService = productService;
	}

	public List<Basket> getAllBasketWithParam(Optional<Long> userId, Optional<Long> productId) {
		if (userId.isPresent() && productId.isPresent()) {
			return basketRepository.findByUserIdAndProductId(userId.get(), productId.get());
		} else if (userId.isPresent()) {
			return basketRepository.findByUserId(userId.get());
		} else if (productId.isPresent()) {
			return basketRepository.findByProductId(productId.get());
		} else {
			return basketRepository.findAll();
		}
	}

	public Basket getOneBasketById(Long basketId) {
		return basketRepository.findById(basketId).orElse(null);
	}

	public Basket createOneBasket(BasketCreateRequest request) {
		User user = userService.getOneUser(request.getUserId());
		Product product = productService.getOneProduct(request.getProductId());
		if (user != null && product != null) {
			Basket basketToSave = new Basket();
			basketToSave.setProductId(product);
			basketToSave.setUserId(user);
			basketToSave.setState(request.getState());
			return basketRepository.save(basketToSave);
		} else {
			return null;
		}

	}

	public Basket updateOneBasketById(Long basketId, BasketUpdateRequest request) {
		Optional<Basket> basket = basketRepository.findById(basketId);
		if (basket.isPresent()) {
			Basket basketToUpdate = basket.get();
			basketToUpdate.setState(request.getState());
			return basketRepository.save(basketToUpdate);
		} else
			return null;
	}

	public void deleteOneBasketById(Long basketId) {
		basketRepository.deleteById(basketId);
	}

}
