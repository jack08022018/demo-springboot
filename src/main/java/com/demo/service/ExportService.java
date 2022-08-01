package com.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public interface ExportService {
    public ByteArrayInputStream exportExcel(MultipartFile file) throws Exception;
}
