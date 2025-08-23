package com.example.demo.DTO;

public class AuthResponse {

    private String token;
    private String tokenType = "Bearer";
    private long expiresIn;
    private long userId;

    public AuthResponse() { }

    public AuthResponse(String token, long expiresIn, long userId) {
        this.token     = token;
        this.expiresIn = expiresIn;
        this.userId    = userId;
    }

    public String getToken()               { return token;     }
    public void setToken(String token)     { this.token = token; }
    public String getTokenType()           { return tokenType; }
    public long getExpiresIn()             { return expiresIn; }
    public void setExpiresIn(long seconds) { this.expiresIn = seconds; }
    public long getUserId()                { return userId;    }
    public void setUserId(long userId)     { this.userId = userId; }
}
