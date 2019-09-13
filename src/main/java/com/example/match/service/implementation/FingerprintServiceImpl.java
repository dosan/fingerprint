package com.example.match.service.implementation;

import com.example.match.model.Fingerprint;
import com.example.match.model.User;
import com.example.match.repository.FingerprintRepository;
import com.example.match.service.FingerprintService;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FingerprintServiceImpl implements FingerprintService {

    @Autowired
    FingerprintRepository fingerprintRepository;

    @Override
    public void saveFingerprint(FingerprintTemplate template, User user) {
        Date date = new Date(System.currentTimeMillis());
        Fingerprint fingerprint = new Fingerprint();
        fingerprint.setName(user.getName());
        fingerprint.setTemplate(template.serialize());
        fingerprint.setDate(date);
        fingerprint.setUser(user);
        fingerprintRepository.save(fingerprint);
    }

    @Override
    public void saveSerializeFingerTemplate(MultipartFile file,User user) throws IOException {
        byte[] probeImage = file.getBytes();
        FingerprintTemplate probe = new FingerprintTemplate()
                .dpi(500)
                .create(probeImage);
        saveFingerprint(probe,user);
    }

    @Override
    public User find(FingerprintTemplate probe, Iterable<Fingerprint> candidates) {
        FingerprintMatcher matcher = new FingerprintMatcher()
                .index(probe);
        Fingerprint match = null;
        double high = 0;
        FingerprintTemplate tempTemplate = new FingerprintTemplate();
        FingerprintTemplate candidateTemplate;
        for (Fingerprint candidate : candidates) {
            candidateTemplate = tempTemplate.deserialize(candidate.getTemplate());
            double score = matcher.match(candidateTemplate);
            if (score > high) {
                high = score;
                match = candidate;
            }
        }
        double threshold = 40;
        if(high >= threshold) {
            return match.getUser();
        }
        return null;
    }

    @Override
    public User compare(MultipartFile file) throws IOException {
        byte[] probeImage = file.getBytes();
        FingerprintTemplate probe = new FingerprintTemplate()
                .dpi(500)
                .create(probeImage);
        Iterable<Fingerprint> fingerprints = fingerprintRepository.findAll();
        return find(probe,fingerprints);
    }
}
