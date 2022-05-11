package com.demo.repository.myDB;

import com.demo.dto.UsersClass;
import com.demo.dto.UsersInterface;
import com.demo.entity.mydb.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    @Query(nativeQuery = true, value = ""
            +   "select A.FULL_NAME, b.JOB_NAME, A.ID "
            +   "from users A "
            +   "   LEFT JOIN job B "
            +   "       ON B.id = A.job_id "
            +   "WHERE 1=1 ")
//            +   "WHERE A.id = :#{#params.id} ")
    List<Tuple> getUserNative(@Param("params") UsersEntity params);

    @Query(nativeQuery = true, value = ""
            +   "select A.FULL_NAME, b.JOB_NAME, A.ID "
            +   "from users A "
            +   "   LEFT JOIN job B "
            +   "       ON B.id = A.job_id "
            +   "WHERE 1=1 ")
    List<UsersInterface> getUserInterface(@Param("params") UsersEntity params);
}
