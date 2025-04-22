package org.example.bootswagger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class StorageService {
    @Value("${supabase.url}")
    private String url;
    @Value("${supabase.access-key}")
    private String accessKey;
    @Value("${supabase.pdf-bucket-name}")
    private String pdfBucketName;
    @Value("${supabase.img-bucket-name}")
    private String imgBucketName;
    @Value("${supabase.sheet-bucket-name}")
    private String sheetBucketName;


    public String upload(MultipartFile file) throws Exception {
        return "";
    }
}
