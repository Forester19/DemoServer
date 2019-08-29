package com.realserver.service.impl;

import com.realserver.model.Role;
import com.realserver.model.Status;
import com.realserver.model.User;
import com.realserver.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;


    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setFirstName("");
        user.setLastName("");
        user.setEmail("");

       User registeredUser = userRepository.save(user);
        return user;
    }

    public List<User> getAll(){
       return userRepository.findAll();
    }

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User findById(Long id) {
        return null;
    }

    public void delete(Long id) {

    }
}
