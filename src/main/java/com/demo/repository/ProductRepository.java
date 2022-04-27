package com.demo.repository;

import com.demo.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDto, Long> {
    @Query(nativeQuery = true, value = ""
            +   "SELECT A.* "
            +   "FROM PRODUCT A "
            +   "WHERE (:#{#params.name} IS NULL OR A.NAME LIKE %:#{#params.name}%) ",
        countQuery = ""
            +   "SELECT COUNT(*) "
            +   "FROM PRODUCT A "
            +   "WHERE (:#{#params.name} IS NULL OR A.NAME LIKE %:#{#params.name}%) ")
    Page<ProductDto> getProductList(@Param("params") ProductDto params, Pageable pageable);
}
