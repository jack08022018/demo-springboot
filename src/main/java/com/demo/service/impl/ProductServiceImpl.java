package com.demo.service.impl;

import com.demo.configuration.exceptionHandler.exception.NoRollbackException;
import com.demo.entity.mydb.ProductEntity;
import com.demo.entity.mydb.UserDetailsEntity;
import com.demo.entity.mydb.UsersEntity;
import com.demo.dto.ActorDto;
import com.demo.entity.sakila.ActorEntity;
import com.demo.repository.myDB.ProductRepository;
import com.demo.repository.myDB.UserDetailsRepository;
import com.demo.repository.myDB.UsersRepository;
import com.demo.repository.sakila.ActorRepository;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

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
    @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
    public void addUser() throws Exception {
        Optional<UsersEntity> entity = usersRepository.findById(4L);
        entity.ifPresent(s -> {
            s.setFullName("Thùy Nhung");
            s.setEmail("nhung@gmail.com");
            usersRepository.save(s);
        });

        try {
            int a = 1/0;
        }catch (Exception e) {
            throw new NoRollbackException(e.getMessage());
        }

        Optional<ActorEntity> actor = actorRepository.findById(1);
        actor.ifPresent(s -> {
            s.setActorId(1);
            s.setFirstName("PENELOPE");
            actorRepository.save(s);
        });
    }

    @Override
    public Page<UserDetailsEntity> getUserDetailsList(UserDetailsEntity dto) {
        Pageable pageable = PageRequest.of(dto.getCurrentPage(), dto.getPageSize());
        return userDetailsRepository.getUserDetailsList(pageable);
    }

    @Override
    public List<ActorDto> getListFilmByActor() {
        return actorRepository.getListFilmByActor();
    }

}
