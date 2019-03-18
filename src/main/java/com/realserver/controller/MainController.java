package com.realserver.controller;

import com.realserver.exceptions.NotFoundException;
import com.realserver.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("message")
public class MainController {
    public List<User> listUsers = new ArrayList<User>(){{
        add(new User(1, "user1","password1"));
        add(new User(2, "user2","password1"));
        add(new User(3, "user3","password1"));
        add(new User(4, "user4","password1"));
        add(new User(5, "user5","password1"));
    }};
    @GetMapping
    public List<User> message(){
        return listUsers;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id){
        return listUsers.stream().filter(useR -> useR.getId() == id).findFirst().orElseThrow(NotFoundException::new);
    }

}
