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
public class LeaveServiceImpl implements OwnerEntryService {

    @Autowired
    OwnerEntryRepository ownerEntryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void register(Owner owner,Integer id) {
        Date date = new Date(System.currentTimeMillis());
        OwnerEntry leave = new OwnerEntry();
        leave.setTime(date);
        leave.setStatus("Вышел");
        leave.setOwner(owner);
        leave.setOrganization(organizationRepository.getOrganizationById(1));
        leave.setType(1);
        ownerEntryRepository.save(leave);
    }
}
