package com.demo.service;

import com.demo.dto.ProductDto;
import com.demo.dto.UsersEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductDto> getProductList(ProductDto dto);
    List<UsersEntity> getUsers();
}
