package com.inventory.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryResponse {
	private String skuCode;
	private boolean isInStock;
}
