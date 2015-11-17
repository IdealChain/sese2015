package sese2015.g3.goldenlion.security.rest.domain.response;

import java.util.Map;

public class LoginResponse {
    private final String username;

    private final Map<String, Boolean> roles;

    private final String token;

    public LoginResponse(String userName, Map<String, Boolean> roles, String token) {
        this.username = userName;
        this.roles = roles;
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }

    public Map<String, Boolean> getRoles() {
        return this.roles;
    }

    public String getToken() {
        return this.token;
    }
}
