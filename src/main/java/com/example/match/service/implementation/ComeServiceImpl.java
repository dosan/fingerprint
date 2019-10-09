package com.example.match.service.implementation;

import com.example.match.model.Owner;
import com.example.match.model.OwnerEntry;
import com.example.match.repository.OrganizationRepository;
import com.example.match.repository.OwnerEntryRepository;
import com.example.match.repository.UserRepository;
import com.example.match.service.OwnerEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComeServiceImpl implements OwnerEntryService {

    @Autowired
    OwnerEntryRepository ownerEntryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void register(Owner owner,Integer organizationId) {
        long HOUR = 3600*1000;
        Date newDate = new Date(System.currentTimeMillis());
        Date date = new Date(newDate.getTime() + 6 * HOUR);
        //Date dateAlmaty = new Date(date.getTime() + 2*);
        OwnerEntry come = new OwnerEntry();
        come.setTime(date);
        come.setStatus("Вошел");
        come.setOwner(owner);
        come.setOrganization(organizationRepository.getOrganizationById(organizationId));
        come.setType(0);
        ownerEntryRepository.save(come);
    }
}
