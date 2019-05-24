package com.realserver.controller;

import com.realserver.model.Role;
import com.realserver.model.User;
import com.realserver.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {
    private List<User> list = new ArrayList<>();
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    @PostConstruct
    private void init(){
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        for(User userFromList: list){
            if(user.getLogin().equals(userFromList.getLogin()) && user.getPassword().equals(userFromList.getPassword())){
                return userFromList;
            }
        }
        return new User();
    }
}
