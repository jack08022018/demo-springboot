package com.demo.controller;


import com.demo.repository.employee.dto.EmployeeInfo;
import com.demo.repository.employee.entity.SalariesEntity;
import com.demo.repository.realdb.MMusicRepository;
import com.demo.repository.realdb.entity.MMusicEntity;
import com.demo.repository.sakila.dto.MovieRentalInfo;
import com.demo.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private MMusicRepository mMusicRepository;

    @Autowired
    private ApiService apiService;

    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @PostMapping(value = "/groceries")
    public List<MMusicEntity> groceries() {
        MMusicEntity e = MMusicEntity.builder()
                .name("a")
                .labelName("label")
                .build();
//        return Arrays.asList(e);
        return mMusicRepository.findAll();
    }

    @PostMapping("/getProductData")
    JsonNode getProductData() throws JsonProcessingException {
        String jsonStr = "{\"ownerArray\":[{\"id\":1,\"value\":\"John Nash\"},{\"id\":2,\"value\":\"Leonhard Euler\"},{\"id\":3,\"value\":\"Alan Turing\"}],\"categoryArray\":[{\"id\":1,\"value\":\"Clothing\"},{\"id\":2,\"value\":\"Jewelery\"},{\"id\":3,\"value\":\"Accessory\"}]}";
        JsonNode jsonNode = mapper.readTree(jsonStr);
//        String json = "{\"id\": 49, \"name\": \"小林　利奈\", \"type\": 1}";
//        return mapper.readTree(json);
        return jsonNode;
    }

    @PostMapping(value = "/getMusicList")
    public Page<MMusicEntity> getMusicList(@RequestBody MMusicEntity dto) {
        return apiService.getMusicList(dto);
    }

    @GetMapping(value = "/getRentalMovies")
    public List<MovieRentalInfo> getRentalMovies(@RequestBody ModelMap params) {
        return apiService.getRentalMovies(params);
    }

    @GetMapping(value = "/getSalaryByAmount")
    public List<EmployeeInfo> getSalaryByAmount(@RequestBody ModelMap params) {
        return apiService.getEmployeeSalary((Integer) params.get("amount"));
    }

}
