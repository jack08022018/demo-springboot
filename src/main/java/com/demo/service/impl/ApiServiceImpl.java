package com.demo.service.impl;

import com.demo.repository.employee.SalariesRepository;
import com.demo.repository.employee.dto.EmployeeInfo;
import com.demo.repository.employee.entity.SalariesEntity;
import com.demo.repository.realdb.MMusicRepository;
import com.demo.repository.realdb.entity.MMusicEntity;
import com.demo.repository.sakila.RentalRepository;
import com.demo.repository.sakila.dto.MovieRentalInfo;
import com.demo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private MMusicRepository mMusicRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private SalariesRepository salariesRepository;

    @Override
    public Page<MMusicEntity> getMusicList(MMusicEntity dto) {
        Pageable pageable = PageRequest.of(dto.getCurrentPage(), dto.getPageSize());
        return mMusicRepository.getMusicList(dto, pageable);
    }

    @Override
    public List<MMusicEntity> getMusicByName(String name) {
        return mMusicRepository.getMusicByName(name);
    }

    @Override
    public MMusicEntity getOne(Integer id) throws FileNotFoundException {
        return mMusicRepository.findById(id)
                .orElseThrow(FileNotFoundException::new);
    }

    @Override
    public List<MovieRentalInfo> getRentalMovies(ModelMap params) {
        return rentalRepository.getRentalMovies((String) params.get("title"));
    }

    @Override
    public List<EmployeeInfo> getEmployeeSalary(Integer amount) {
        return salariesRepository.getEmployeeSalary(amount);
    }
}
