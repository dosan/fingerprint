package com.example.match.service.implementation;

import com.example.match.model.TempClass;
import com.example.match.repository.GroupRepository;
import com.example.match.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void save(int organizationId, int ownerId) {
        TempClass group = new TempClass();
        group.setOrg(organizationId);
        group.setOwe(ownerId);
        groupRepository.save(group);
    }

    @Override
    public void delete(int ownerId) {
        TempClass group = groupRepository.findByOwe(ownerId);
        groupRepository.delete(group);
    }

    @Override
    public Iterable<TempClass> findOwnersByOrganization(int organizationId) {
        return groupRepository.findAllByOrg(organizationId);
    }
}
