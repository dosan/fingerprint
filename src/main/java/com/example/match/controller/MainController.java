package com.example.match.controller;
;
import com.example.match.service.FileStorageService;
import com.example.match.service.FingerprintService;
import com.example.match.service.ImageService;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FingerprintService fingerprintService;

    @PostMapping
    public String fingerprintCompare(@RequestParam("file") MultipartFile file) throws IOException { ;
        String match = fingerprintService.compare(file);
        System.out.println(match);
        return match;
    }
    @PostMapping("/save")
    public String fingerprintSave(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name) throws IOException {
        System.out.println(name);
        Map<String,String> fileName = fileStorageService.storeFile(file);
        imageService.saveImage(fileName.get("name"),fileName.get("path"),file.getContentType(), (int)file.getSize());
        fingerprintService.saveSerializeFingerTemplate(fileName.get("path"),name);
        return "Saved";
    }
}
