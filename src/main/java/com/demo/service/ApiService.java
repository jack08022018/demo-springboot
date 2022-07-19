package com.demo.service;

import com.demo.repository.employee.dto.EmployeeInfo;
import com.demo.repository.employee.entity.SalariesEntity;
import com.demo.repository.realdb.entity.MMusicEntity;
import com.demo.repository.sakila.dto.MovieRentalInfo;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

import java.io.FileNotFoundException;
import java.util.List;

public interface ApiService {
    Page<MMusicEntity> getMusicList(MMusicEntity dto);
    List<MMusicEntity> getMusicByName(String name);
    MMusicEntity getOne(Integer id) throws FileNotFoundException;
    List<MovieRentalInfo> getRentalMovies(ModelMap params);
    List<EmployeeInfo> getEmployeeSalary(Integer amount);
}
