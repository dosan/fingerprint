package com.example.match.service.implementation;

import com.example.match.model.Coming;
import com.example.match.model.Owner;
import com.example.match.repository.ComeRepository;
import com.example.match.repository.UserRepository;
import com.example.match.service.ComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComeServiceImpl implements ComeService {

    @Autowired
    ComeRepository comeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void register(Owner owner) {
        Date date = new Date(System.currentTimeMillis());
        Coming come = new Coming();
        come.setHasCome(date);
        come.setStatus("In-Time");
        come.setOwner(owner);
        comeRepository.save(come);
    }
}
