package com.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;
import com.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ObjectMapper objectMapper;
	private final ProductRepository productRepository;

	public void createProduct(@RequestBody ProductRequest product) {

		log.info("Inside createProduct");
		Product model = objectMapper.convertValue(product, Product.class);
		Product fromDB = productRepository.save(model);
		log.info("Product created, {}", fromDB.getId());
	}

	public List<ProductResponse> getAllProducts() {
		log.info("Inside getAllProducts");
		return productRepository.findAll().stream()
				.map(product -> objectMapper.convertValue(product, ProductResponse.class)).collect(Collectors.toList());
	}

}
