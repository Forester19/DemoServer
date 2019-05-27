package com.realserver.utils;

import com.realserver.model.Role;
import com.realserver.model.Token;
import com.realserver.model.User;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TokenUtilsTest {

    @Test
    public void getToken() {
        User user = new User();
        user.setId(1);
        user.setLogin("Vlad");
        user.setPassword("password");
        user.setRole(Role.USER);
        Token token = TokenUtils.getInstance().getToken(user);
        assertEquals(token.getExpirationLocalDateTime().getHour(), LocalDateTime.now().plusHours(1).getHour());
    }
}