package com.example.match.service.implementation;

import com.example.match.model.Fingerprint;
import com.example.match.model.Owner;
import com.example.match.repository.FingerprintRepository;
import com.example.match.service.FingerprintService;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@Service
public class FingerprintServiceImpl implements FingerprintService {

    @Autowired
    FingerprintRepository fingerprintRepository;

    @Override
    public void saveFingerprint(FingerprintTemplate template, Owner owner) {
        Date date = new Date(System.currentTimeMillis());
        Fingerprint fingerprint = new Fingerprint();
        fingerprint.setName(owner.getName());
        fingerprint.setTemplate(template.serialize());
        fingerprint.setDate(date);
        fingerprint.setOwner(owner);
        fingerprintRepository.save(fingerprint);
    }

    @Override
    public Integer countByUser(Owner owner) {
        return (int)fingerprintRepository.countByOwner(owner);
    }

    @Override
    public void saveSerializeFingerTemplate(MultipartFile file, Owner owner) throws IOException {
        byte[] probeImage = file.getBytes();
        FingerprintTemplate probe = new FingerprintTemplate()
                .dpi(500)
                .create(probeImage);
        saveFingerprint(probe, owner);
    }

    @Override
    public Owner find(FingerprintTemplate probe, Iterable<Fingerprint> candidates) {
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
            return match.getOwner();
        }
        return null;
    }

    @Override
    public Owner compare(MultipartFile file) throws IOException {
        byte[] probeImage = file.getBytes();
        FingerprintTemplate probe = new FingerprintTemplate()
                .dpi(500)
                .create(probeImage);
        Iterable<Fingerprint> fingerprints = fingerprintRepository.findAll();
        return find(probe,fingerprints);
    }
}
