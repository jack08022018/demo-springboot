package com.demo.service.impl;

import com.demo.dto.ProductDto;
import com.demo.dto.UsersEntity;
import com.demo.repository.ProductRepository;
import com.demo.repository.UsersRepository;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Page<ProductDto> getProductList(ProductDto dto) {
        Pageable pageable = PageRequest.of(dto.getCurrentPage(), dto.getPageSize());
        return productRepository.getProductList(dto, pageable);
    }

    @Override
    public List<UsersEntity> getUsers() {
        return usersRepository.findAll();
    }
}
