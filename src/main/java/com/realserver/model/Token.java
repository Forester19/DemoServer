package com.realserver.model;

import java.time.LocalDateTime;

public class Token {
    private User user;
    private LocalDateTime loginLocalDateTime; // login date
    private long tempTokenExpiration = 3_600_000;   // how long should exist token in milliseconds
    private LocalDateTime expirationLocalDateTime; // date of temp token expiration
    private String secret = "secret";


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoginLocalDateTime() {
        return loginLocalDateTime;
    }

    public void setLoginLocalDateTime(LocalDateTime loginLocalDateTime) {
        this.loginLocalDateTime = loginLocalDateTime;
    }

    public long getTempTokenExpiration() {
        return tempTokenExpiration;
    }

    public void setTempTokenExpiration(long tempTokenExpiration) {
        this.tempTokenExpiration = tempTokenExpiration;
    }

    public LocalDateTime getExpirationLocalDateTime() {
        return expirationLocalDateTime;
    }

    public void setExpirationLocalDateTime(LocalDateTime expirationLocalDateTime) {
        this.expirationLocalDateTime = expirationLocalDateTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "user=" + user +
                ", loginLocalDateTime=" + loginLocalDateTime +
                ", tempTokenExpiration=" + tempTokenExpiration +
                ", expirationLocalDateTime=" + expirationLocalDateTime +
                '}';
    }
}
