package com.realserver.controller;

import com.realserver.model.SimpleUser;
import com.realserver.model.User;
import com.realserver.model.dto.AuthenticationRequestDto;
import com.realserver.security.jwt.JwtTokenProvider;
import com.realserver.service.impl.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "*")
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(UserService userService) {
         this.userService = userService;
    }

    @GetMapping
    public ResponseEntity get(){
        Map<Object,Object> response = new HashMap<>();
        response.put("username", "username");
        response.put("token", "some token");
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "log-in")
    @PostMapping
    public SimpleUser postLogIn(@RequestBody SimpleUser userFromClient){
        System.out.println( "query from client user:" + userFromClient.toString());
        if(userFromClient.getLogin().equals("aaa") && userFromClient.getPassword().equals("aaa")){
            System.out.println("return valid user");
            return userFromClient;
        }
        return new SimpleUser();
    }

    @RequestMapping(value = "sign-up")
    @PostMapping
    public SimpleUser postSignUp(@RequestBody SimpleUser userFromClient){
        System.out.println("Got query from client user:" + userFromClient.toString());
        User userFromDBuserService = userService.register(new User(userFromClient.getLogin(), userFromClient.getPassword()));
        return new SimpleUser(userFromDBuserService.getUsername(), userFromDBuserService.getPassword());
    }

   /* @PostMapping
    public ResponseEntity logIn(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        String username = authenticationRequestDto.getUsername();
        authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPassword()));

        User user = userService.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        String token = jwtTokenProvider.crateToken(username,user.getRole());
        Map<Object,Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
     return  ResponseEntity.ok(response);
    }*/
}
