package com.inventory;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class InventryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository) {
		return args -> {
			Inventory i = new Inventory();
			i.setSkuCode("iphone_13");
			i.setQuantity(100);
			Inventory i2 = new Inventory();
			i2.setSkuCode("iphone_13_Red");
			i2.setQuantity(0);
			repository.saveAll(List.of(i, i2));

		};
	}
}
