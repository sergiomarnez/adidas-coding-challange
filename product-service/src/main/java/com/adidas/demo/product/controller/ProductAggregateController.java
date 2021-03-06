package com.adidas.demo.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.demo.product.dto.PartialProductAggregate;
import com.adidas.demo.product.dto.ProductAggregate;
import com.adidas.demo.product.exception.PartialAggregateException;
import com.adidas.demo.product.service.ProductService;
import com.adidas.demo.product.service.impl.ProductAggregateServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductAggregateController {

	private ProductService<ProductAggregate> service;
	
	public ProductAggregateController(ProductAggregateServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping("/product/{product_id}")
	public ProductAggregate getProductAggregate(@PathVariable("product_id") String productId) {
		log.info("Handle request: Get ProductAggregate with id {}", productId);
		return service.get(productId);
	}
	
	@ExceptionHandler(PartialAggregateException.class)
	@ResponseStatus(HttpStatus.OK)
	public PartialProductAggregate handlePartialProductException(PartialAggregateException pae) {
		return PartialProductAggregate
				.builder()
				.message(pae.getMessage())
				.product(pae.getProduct())
				.build();
	}
}
