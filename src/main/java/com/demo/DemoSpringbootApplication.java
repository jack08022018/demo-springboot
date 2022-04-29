package com.demo;

import com.demo.dto.ProductDto;
import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringbootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootApplication.class, args);
	}

	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
//		ProductDto entity = null;
//		entity = new ProductDto(32, "Personalized 1", "This is a metal 1", "John Brown 1", "jewellery");
//		productRepository.save(entity);
//
//		entity = new ProductDto(32, "Personalized 2", "This is a metal 2", "John Brown 2", "jewellery");
//		productRepository.save(entity);
//
//		entity = new ProductDto(32, "Personalized 3", "This is a metal 3", "John Brown 3", "jewellery");
//		productRepository.save(entity);
//
//		entity = new ProductDto(32, "Personalized 4", "This is a metal 4", "John Brown 4", "jewellery");
//		productRepository.save(entity);
//
//		entity = new ProductDto(32, "Personalized 1", "This is a metal 5", "John Brown 5", "jewellery");
//		productRepository.save(entity);
//
//		entity = new ProductDto(32, "Personalized 6", "This is a metal 6", "John Brown 6", "jewellery");
//		productRepository.save(entity);
	}
}
