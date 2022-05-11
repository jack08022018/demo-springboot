package com.demo.service;

import com.demo.dto.FilmInfo;
import com.demo.dto.UsersClass;
import com.demo.entity.mydb.ProductEntity;
import com.demo.entity.mydb.UserDetailsEntity;
import com.demo.entity.mydb.UsersEntity;
import com.demo.dto.ActorDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductEntity> getProductList(ProductEntity dto);
    List<UsersEntity> getUsers();
    void addUser() throws Exception;
    Page<UserDetailsEntity> getUserDetailsList(UserDetailsEntity dto);
    <T> List<T> getListFilmByActor();
    <T> List<T> userById(UsersEntity params);
}
