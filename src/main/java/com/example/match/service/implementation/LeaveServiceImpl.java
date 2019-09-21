package com.example.match.service.implementation;

import com.example.match.model.Leaving;
import com.example.match.model.Owner;
import com.example.match.repository.LeaveRepository;
import com.example.match.repository.UserRepository;
import com.example.match.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void register(Owner owner) {
        Date date = new Date(System.currentTimeMillis());
        Leaving leave = new Leaving();
        leave.setTimestamp(date);
        leave.setStatus("In-Time");
        leave.setOwner(owner);
        leaveRepository.save(leave);
    }
}
