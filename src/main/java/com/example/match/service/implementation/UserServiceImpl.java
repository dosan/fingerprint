package com.example.match.service.implementation;

import com.example.match.model.Owner;
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
    public Owner saveUser(String name, String surname, String iin) {

        Date date = new Date(System.currentTimeMillis());
        Owner owner = new Owner();
        owner.setName(name);
        owner.setSurname(surname);
        owner.setIin(iin);
        owner.setCreatedAt(date);
        owner.setUpdatedAt(date);
        userRepository.save(owner);
        return owner;
    }

    @Override
    public Owner findById(Integer idx) {
        return userRepository.findById(idx).get();
    }

    @Override
    public Owner findByAttirbutes(String name, String surname, String iin) {
        Owner existingOwner = userRepository.findByNameAndSurnameAndIin(name,surname,iin);
        return existingOwner;
    }
}
