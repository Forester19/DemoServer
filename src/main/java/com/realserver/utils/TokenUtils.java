package com.realserver.utils;

import com.realserver.model.Token;
import com.realserver.model.User;

import java.time.LocalDateTime;

public class TokenUtils implements TokenUtilsAbstract{
    private static TokenUtils ourInstance = new TokenUtils();

    public static TokenUtils getInstance() {
        return ourInstance;
    }

    private TokenUtils() {
    }

    @Override
    public Token getToken(User userDetails) {
        Token token = new Token();
        token.setUser(userDetails);
        LocalDateTime now = LocalDateTime.now();
        token.setLoginLocalDateTime(now);
        LocalDateTime calculatedExpirationDateTime = now.plusSeconds(token.getTempTokenExpiration()/1000);
        token.setExpirationLocalDateTime(calculatedExpirationDateTime);
        return token;
    }

    @Override
    public Token getToken(User userDetails, Long expiration) {
        return null;
    }

    @Override
    public boolean validate(Token token) {
        return false;
    }

    @Override
    public User getUserFromToken(Token token) {
        return null;
    }
}
