package com.demo.dto;

import org.springframework.beans.factory.annotation.Value;

public class UsersClass {
    @Value("#{target.ID}")
    private Integer id;

    @Value("#{target.FULL_NAME}")
    private String fullName;

    @Value("#{target.JOB_NAME}")
    private String jobName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
