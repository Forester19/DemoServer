package com.realserver.controller;

import com.realserver.model.User;
import com.realserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @Autowired
    public UserController(UserService UserService) {
       this.userService = UserService;
    }

    @PostConstruct
    private void init(){
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        for(User userFromList: list){
            if(user.getUsername().equals(userFromList.getUsername()) && user.getPassword().equals(userFromList.getPassword())){
                return userFromList;
            }
        }
        return new User();
    }
}
