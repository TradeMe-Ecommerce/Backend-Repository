package com.example.demo.DTO;

public class AuthRequest {

    private String username;      // o email, según tu UserDetailsService
    private String password;

    /* getters & setters */
    public String getUsername()               { return username; }
    public void setUsername(String username)  { this.username = username; }

    public String getPassword()               { return password; }
    public void setPassword(String password)  { this.password = password; }
}
