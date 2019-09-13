package com.example.match.service.implementation;

import com.example.match.model.Coming;
import com.example.match.model.Leaving;
import com.example.match.model.User;
import com.example.match.repository.ComeRepository;
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
    public void register(User user) {
        Date date = new Date(System.currentTimeMillis());
        Leaving leave = new Leaving();
        leave.setTimestamp(date);
        leave.setStatus("In-Time");
        leave.setUser(user);
        leaveRepository.save(leave);
    }
}
