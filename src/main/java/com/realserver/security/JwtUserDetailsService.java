package com.realserver.security;

import com.realserver.model.User;
import com.realserver.security.jwt.JwtUser;
import com.realserver.security.jwt.JwtUserFactory;
import com.realserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("User not found " + userName);
        }
        JwtUser jwtUser = JwtUserFactory.create(user);

        return null;
    }
}
