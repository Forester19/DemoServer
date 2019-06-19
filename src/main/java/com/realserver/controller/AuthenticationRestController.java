package com.realserver.controller;

import com.realserver.model.User;
import com.realserver.model.dto.AuthenticationRequestDto;
import com.realserver.security.jwt.JwtTokenProvider;
import com.realserver.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "log-in")
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManger;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManger, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManger = authenticationManger;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

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
    }
}
