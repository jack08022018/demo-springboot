package com.demo.service;

import com.demo.dto.mydb.ProductEntity;
import com.demo.dto.mydb.UsersEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductEntity> getProductList(ProductEntity dto);
    List<UsersEntity> getUsers();
    void addUser();
}
