package com.example.match.service;

import com.example.match.model.Fingerprint;
import com.example.match.model.Owner;
import com.machinezoo.sourceafis.FingerprintTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FingerprintService {
    void saveFingerprint(FingerprintTemplate template, Owner owner);
    void saveSerializeFingerTemplate(MultipartFile file, Owner owner) throws IOException;
    Owner compare(MultipartFile file,int organizationId) throws IOException;
    Owner find(FingerprintTemplate probe, Iterable<Fingerprint> candidates);
    Integer countByUser(Owner owner);
}