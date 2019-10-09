package com.example.match.service.implementation;

import com.example.match.model.Fingerprint;
import com.example.match.model.TempClass;
import com.example.match.model.Owner;
import com.example.match.repository.FingerprintRepository;
import com.example.match.repository.GroupRepository;
import com.example.match.repository.UserRepository;
import com.example.match.service.FingerprintService;
import com.example.match.service.GroupService;
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
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveFingerprint(FingerprintTemplate template, Owner owner) {
        long HOUR = 3600*1000;
        Date newDate = new Date(System.currentTimeMillis());
        Date date = new Date(newDate.getTime() + 6 * HOUR);
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
    public Owner compare(MultipartFile file, int organizationId) throws IOException {
        byte[] probeImage = file.getBytes();
        FingerprintTemplate probe = new FingerprintTemplate()
                .dpi(500)
                .create(probeImage);
        Iterable<TempClass> groups =groupRepository.findAllByOrg(organizationId);
        Iterable<Owner> owners;
        Iterable<Fingerprint> fingerprints;
        List<Owner> ownersList = new ArrayList<>();
        List<Fingerprint> fingerprintsList = new ArrayList<>();
        //Iterable<Fingerprint> fingerprints = fingerprintRepository.findAll();

        for (TempClass group : groups) {
            Owner owner = userRepository.findById(group.getOwe()).get();
            //System.out.println(owner.getName());
            ownersList.add(owner);
        }
        owners = ownersList;
        for(Owner owner : owners) {
            Iterable<Fingerprint> tempFingerprint = fingerprintRepository.findAllByOwner(owner);
            for(Fingerprint finger : tempFingerprint) {
                fingerprintsList.add(finger);
                //System.out.println(finger.getName());
            }
        }
        fingerprints = fingerprintsList;
        System.out.println(fingerprints);
        return find(probe,fingerprints);
    }
}
