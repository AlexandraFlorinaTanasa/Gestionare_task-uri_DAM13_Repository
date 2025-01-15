package org.example.web;

import org.springframework.stereotype.Component;

@Component
public class SessionCredentials {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public SessionCredentials() {
        super();
    }
    //
    public void resetCredentials() {
        this.username = null;
        this.password = null;
    }
    //
    public boolean isAuthenticated() {
        return (this.username != null && this.password != null);
    }
    //
    @Override
    public String toString() {
        return "SessionCredentials [username=" + username + ", password=" + password + "]";
    }

}
