package sese2015.g3.goldenlion.security.rest.domain.response;

public class RegistrationResponse {
    private String password;

    public RegistrationResponse(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
