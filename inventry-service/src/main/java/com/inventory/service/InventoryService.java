package com.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.InventoryResponse;
import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

	private final InventoryRepository repository;

	@Transactional(readOnly = true)
	@SneakyThrows
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		log.info("Wait started");
//		Thread.sleep(10000);
		log.info("Wait end");

		return repository.findBySkuCodeIn(skuCode).stream().map(this::toResponse).collect(Collectors.toList());

	}

	private InventoryResponse toResponse(Inventory i) {
		return InventoryResponse.builder().skuCode(i.getSkuCode()).isInStock(i.getQuantity() > 0).build();
	}

}
