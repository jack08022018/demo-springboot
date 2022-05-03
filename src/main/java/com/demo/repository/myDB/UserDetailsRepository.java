package com.demo.repository.myDB;

import com.demo.dto.mydb.ProductEntity;
import com.demo.dto.mydb.UserDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {
    @Query(nativeQuery = true, value = ""
            +   "SELECT A.* "
            +   "FROM USER_DETAILS A "
            +   "WHERE 1=1 ",
        countQuery = ""
            +   "SELECT COUNT(*) "
            +   "FROM USER_DETAILS A "
            +   "WHERE 1=1 ")
    Page<UserDetailsEntity> getUserDetailsList(Pageable pageable);
}
