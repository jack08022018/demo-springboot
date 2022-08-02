package com.demo.controller;


import com.demo.pdf.PDFGenerator;
import com.demo.repository.employee.dto.EmployeeInfo;
import com.demo.repository.employee.entity.SalariesEntity;
import com.demo.repository.realdb.MMusicRepository;
import com.demo.repository.realdb.entity.MMusicEntity;
import com.demo.repository.sakila.dto.MovieRentalInfo;
import com.demo.service.ApiService;
import com.demo.service.ExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private MMusicRepository mMusicRepository;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ExportService exportService;

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

    @GetMapping(value = "/exportPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportPdf() throws Exception {
        ByteArrayInputStream bis = PDFGenerator.getPdfReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/exportExcel")
    public ResponseEntity<Resource> exportExcel(@RequestParam("file") MultipartFile file) throws Exception {
        InputStreamResource fileExport = new InputStreamResource(exportService.exportExcel(file));
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tutorials.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(fileExport);
    }

}
