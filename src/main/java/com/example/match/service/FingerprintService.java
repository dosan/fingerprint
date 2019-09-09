package com.example.match.service;

import com.example.match.model.Fingerprint;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

public interface FingerprintService {
    void saveFingerprint(FingerprintTemplate template,String name);
    void saveSerializeFingerTemplate(String pathToImage,String name) throws IOException;
    String compare(MultipartFile file) throws IOException;
    String find(FingerprintTemplate probe, Iterable<Fingerprint> candidates);
}