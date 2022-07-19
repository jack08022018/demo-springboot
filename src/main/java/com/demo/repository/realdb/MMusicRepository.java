package com.demo.repository.realdb;

import com.demo.repository.realdb.entity.MMusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MMusicRepository extends JpaRepository<MMusicEntity, Integer> {

    @Query(nativeQuery = true, value = ""
            +   "SELECT A.* "
            +   "FROM m_music A "
            +   "WHERE (:#{#params.name} IS NULL OR A.name LIKE %:#{#params.name}%) ",
        countQuery = ""
            +   "SELECT COUNT(*) "
            +   "FROM m_music A "
            +   "WHERE (:#{#params.name} IS NULL OR A.name LIKE %:#{#params.name}%) ")
    Page<MMusicEntity> getMusicList(@Param("params") MMusicEntity params, Pageable pageable);

    @Query(nativeQuery = true, value = ""
            +   "SELECT A.* "
            +   "FROM m_music A "
            +   "WHERE A.name LIKE %:name% ")
    List<MMusicEntity> getMusicByName(@Param("name") String name);
}
