package com.adidas.demo.product.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.adidas.demo.product.client.ProductClient;
import com.adidas.demo.product.client.impl.ProductClientImpl;
import com.adidas.demo.product.dto.Product;
import com.adidas.demo.product.exception.AggregateComponentNotFoundException;
import com.adidas.demo.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService<Product> {

	@Value("${api.product.url}")
	private String productApiUrlTemplate;
	private ProductClient<Product> client;
	
	public ProductServiceImpl(ProductClientImpl client) {
		this.client = client;
	}
	
	@Override
	public Product get(String id) throws AggregateComponentNotFoundException {
		log.info("Get Product with id {}", id);
		return client.getResourceFromUrl(productApiUrlTemplate, id);
	}

}
