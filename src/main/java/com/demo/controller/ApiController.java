package com.demo.controller;


import com.demo.entity.mydb.ProductEntity;
import com.demo.entity.mydb.UserDetailsEntity;
import com.demo.entity.mydb.UsersEntity;
import com.demo.dto.ActorDto;
import com.demo.service.ProductService;
import com.demo.springJMS.JMSProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import com.demo.pdf.PDFGenerator;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JMSProducer jMSProducer;

    @PostMapping(value = "/sendMessage")
    public void sendMessage() {
        jMSProducer.sendMessage("1");
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
    public List<UsersEntity> users() {
        return productService.getUsers();
    }

    @PostMapping(value = "/getListFilmByActor")
    public List<ActorDto> getListFilmByActor() {
        return productService.getListFilmByActor();
    }

    @PostMapping("/getProductData")
    JsonNode getProductData() throws JsonMappingException, JsonProcessingException {
        String jsonStr = "{\"ownerArray\":[{\"id\":1,\"value\":\"John Nash\"},{\"id\":2,\"value\":\"Leonhard Euler\"},{\"id\":3,\"value\":\"Alan Turing\"}],\"categoryArray\":[{\"id\":1,\"value\":\"Clothing\"},{\"id\":2,\"value\":\"Jewelery\"},{\"id\":3,\"value\":\"Accessory\"}]}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonStr);
        return jsonNode;
    }

    @PostMapping(value = "/addUser")
    public void addUser() throws Exception {
        productService.addUser();
    }

//    @GetMapping(value = "/exportPdf", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<InputStreamResource> exportPdf() throws Exception {
//        ByteArrayInputStream bis = PDFGenerator.getPdfReport();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=customers.pdf");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//    }
}
