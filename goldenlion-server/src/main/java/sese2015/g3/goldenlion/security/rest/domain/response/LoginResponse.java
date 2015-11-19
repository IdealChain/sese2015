package sese2015.g3.goldenlion.security.rest.domain.response;

public class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
