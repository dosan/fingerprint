package com.example.match.service;

import com.example.match.model.User;

public interface UserService {
    User saveUser(String name,String surname, String iin);
    User findById(Integer idx);
    User findByAttirbutes(String name,String surname,String iin);
}
