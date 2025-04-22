package org.example.bootswagger.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.bootswagger.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/file")
public class FileController {
    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> upload(@RequestParam("files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
            String dbFileName = storageService.upload(file);
            log.info(dbFileName);
        }
        return ResponseEntity.ok().build();
    }
}
