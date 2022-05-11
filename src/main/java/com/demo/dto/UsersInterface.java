package com.demo.dto;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;

public interface UsersInterface {
    @Value("#{target.ID}")
    Integer getId();

    @Value("#{target.FULL_NAME}")
    String getFullName();

    @Value("#{target.JOB_NAME}")
    String getJobName();

    Job getJob();

    interface Job {
        Long getId();
        String getJobName();
    }
}
