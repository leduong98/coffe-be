package com.example.coffeebe.app.controllers;

import com.example.coffeebe.app.dtos.responses.FileResponse;
import com.example.coffeebe.domain.services.impl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resources/")
                .path(fileName)
                .toUriString();

        return new FileResponse(fileName, fileDownloadUri);
    }

}
