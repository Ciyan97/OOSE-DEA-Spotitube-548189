package nl.han.oose.domain.responses;

public class LoginResponse {
    private String token;
    private String user;

    public LoginResponse(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public LoginResponse() { }

    public String getToken() {
        return token;
    }
}
