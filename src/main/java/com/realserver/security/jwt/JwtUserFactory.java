package com.realserver.security.jwt;

import com.realserver.model.Role;
import com.realserver.model.Status;
import com.realserver.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }
    public static JwtUser create(User user){
        return new JwtUser(user.getId(),
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getLastPasswordResetDate(),
                mapToGrantedAthority(user.getRole() ));
    }

    private static List<GrantedAuthority> mapToGrantedAthority(Role userRole){
       List<GrantedAuthority> list = new ArrayList<>();
       list.add(new SimpleGrantedAuthority(userRole.name()));
        return list;
    }
}

