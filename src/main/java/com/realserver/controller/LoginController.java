package com.realserver.controller;

import com.realserver.model.Token;
import com.realserver.model.User;
import com.realserver.utils.TokenUtils;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.net.http.HttpResponse;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("log-in")
public class LoginController {


    @PostMapping
    public Token authenticateUser(@RequestBody User user){
        Token token = TokenUtils.getInstance().getToken(user);
        return token;
    }

}
