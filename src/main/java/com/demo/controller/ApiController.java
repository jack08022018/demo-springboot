package com.demo.controller;


import com.demo.entity.mongoLocal.GroceryItem;
import com.demo.entity.mydb.ProductEntity;
import com.demo.entity.mydb.UserDetailsEntity;
import com.demo.entity.mydb.UsersEntity;
import com.demo.repository.mongoLocal.GroceryItemRepository;
import com.demo.service.ProductService;
import com.demo.service.pdf.PDFGenerator;
import com.demo.springJMS.JMSProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JMSProducer jMSProducer;

    @Autowired
    GroceryItemRepository groceryItemRepository;

    @ResponseBody
    @PostMapping(value = "/sendMessage")
    public <T> T sendMessage() throws Exception {
//        jMSProducer.sendMessage("1");
        File file = ResourceUtils.getFile("classpath:static/aa.srt");
        String content = FileUtils.readFileToString(file, "UTF-8");
        List<String> data = Arrays.asList(content.split("\\R"));
//        for (String s : data) {
//            if(s.contains("-->")) {
//                String[] arr = s.split(" --> ");
//                String start = arr[0].replace(",", ".");
//                String end = arr[1].replace(",", ".");
//                Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
//                        .parse("10/11/2000 00:00:07.604" + );
//                continue;
//            }
//
//        }
//        new Date( System.currentTimeMillis() + 5000L)
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse("10/11/2000 00:00:59.604");
        long mili = date.getTime();
        Date date1 = new Date(mili + 1227);
        return (T) date1;
    }

    @PostMapping(value = "/getUserDetailsList")
    public Page<UserDetailsEntity> getUserDetailsList(@RequestBody UserDetailsEntity dto) {
        return productService.getUserDetailsList(dto);
    }

    @PostMapping(value = "/products")
    public Page<ProductEntity> products(@RequestBody ProductEntity dto) {
        return productService.getProductList(dto);
    }

    @PostMapping(value = "/users")
    public <T> List<T> users() {
        return productService.getUsers();
    }

    @ResponseBody
    @PostMapping(value = "/userById")
    public <T> List<T> userById(@RequestBody UsersEntity params) {
        return productService.userById(params);
    }

    @PostMapping(value = "/getListFilmByActor")
    public <T> List<T> getListFilmByActor() {
        return productService.getListFilmByActor();
    }

    @PostMapping("/getProductData")
    JsonNode getProductData() throws JsonMappingException, JsonProcessingException {
        String jsonStr = "{\"ownerArray\":[{\"id\":1,\"value\":\"John Nash\"},{\"id\":2,\"value\":\"Leonhard Euler\"},{\"id\":3,\"value\":\"Alan Turing\"}],\"categoryArray\":[{\"id\":1,\"value\":\"Clothing\"},{\"id\":2,\"value\":\"Jewelery\"},{\"id\":3,\"value\":\"Accessory\"}]}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonStr);
        return jsonNode;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping(value = "/addUser")
    @Transactional(rollbackFor = Exception.class)
    public void addUser() {
//        productService.addUser();
//        mongoTemplate.save(new GroceryItem("AAA", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepository.save(new GroceryItem("AAA", "Whole Wheat Biscuit", 5, "snacks"));
//        int a = 1/0;
    }

    @PostMapping(value = "/groceries")
    public <T> List<T> groceries() {
        return (List<T>) groceryItemRepository.findAll();
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
}
