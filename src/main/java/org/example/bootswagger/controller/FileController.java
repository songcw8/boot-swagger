package org.example.bootswagger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/file")
public class FileController {
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> upload(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
        }
        return ResponseEntity.ok().build();
    }
}
