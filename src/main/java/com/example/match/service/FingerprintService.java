package com.example.match.service;

import com.example.match.model.Fingerprint;
import com.example.match.model.User;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

public interface FingerprintService {
    void saveFingerprint(FingerprintTemplate template,User user);
    void saveSerializeFingerTemplate(MultipartFile file, User user) throws IOException;
    User compare(MultipartFile file) throws IOException;
    User find(FingerprintTemplate probe, Iterable<Fingerprint> candidates);
    Integer countByUser(User user);
}