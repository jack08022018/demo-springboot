package com.demo.repository.myDB;

import com.demo.entity.mydb.JobEntity;
import com.demo.entity.mydb.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

}
