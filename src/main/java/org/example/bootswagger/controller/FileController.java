package org.example.bootswagger.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.bootswagger.model.entity.MyFile;
import org.example.bootswagger.model.repository.MyFileRepository;
import org.example.bootswagger.service.StorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/file")
public class FileController {
    private final StorageService storageService;
    private final MyFileRepository myFileRepository;

    public FileController(StorageService storageService, MyFileRepository myFileRepository) {
        this.storageService = storageService;
        this.myFileRepository = myFileRepository;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> upload(@RequestParam("files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
            String dbFileName = storageService.upload(file);
            log.info(dbFileName);
            MyFile myFile = new MyFile();
            myFile.setFilename(dbFileName);
            myFileRepository.save(myFile);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MyFile>> getAll() {
        return ResponseEntity.ok(myFileRepository.findAll());
    }

    @GetMapping("/{bucketName}/{filename}")
    public ResponseEntity<byte[]> file(@PathVariable String bucketName, @PathVariable String filename) {
        try {
            byte[] fileBytes = storageService.download("%s/%s".formatted(bucketName, filename)); // 버킷 -> 파일 이름 요청

            // 페이지(템플릿)로 응답하지 않고, 데이터로 응답하겠다
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileBytes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
