package com.example.match.service.implementation;

import com.example.match.model.User;
import com.example.match.repository.UserRepository;
import com.example.match.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(String name, String surname, String iin) {
        Date date = new Date(System.currentTimeMillis());
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setIin(iin);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Integer idx) {
        return userRepository.findById(idx).get();
    }
}
