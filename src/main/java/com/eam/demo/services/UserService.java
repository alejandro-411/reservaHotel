package com.eam.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.User;
import com.eam.demo.repository.IUserRepository;

@Service
public class UserService {    
    
    @Autowired
    private IUserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findAUser(Long userId) {
        return userRepository.findById(userId).get();
    }
}
