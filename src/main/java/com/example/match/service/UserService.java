package com.example.match.service;

import com.example.match.model.Owner;

public interface UserService {
    Owner saveUser(String name, String surname, String iin);
    Owner findById(Integer idx);
    Owner findByAttirbutes(String name, String surname, String iin);
}
