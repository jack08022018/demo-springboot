package com.demo.service.impl;

import com.demo.dto.mydb.ProductEntity;
import com.demo.dto.mydb.UsersEntity;
import com.demo.repository.myDB.ProductRepository;
import com.demo.repository.myDB.UsersRepository;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Page<ProductEntity> getProductList(ProductEntity dto) {
        Pageable pageable = PageRequest.of(dto.getCurrentPage(), dto.getPageSize());
        return productRepository.getProductList(dto, pageable);
    }

    @Override
    public List<UsersEntity> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser() {
        UsersEntity entity = new UsersEntity();
        entity.setFullName("Thùy Nhung");
        entity.setEmail("nhung@gmail.com");
        entity.setCreateDate(new Date());
        usersRepository.save(entity);

//        int a = 1/0;

        ProductEntity product = new ProductEntity();
        product.setQty(14);
        productRepository.save(product);
    }
}
