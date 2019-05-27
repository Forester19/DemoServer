package com.realserver.utils;

import com.realserver.model.Token;
import com.realserver.model.User;

public interface TokenUtilsAbstract {
    Token getToken(User userDetails);
    Token getToken(User userDetails, Long expiration);
    boolean validate(Token token);
    User getUserFromToken(Token token);
}
