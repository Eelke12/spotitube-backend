package dea.eelkedejong.spotitube.eigenaar.dto;

import javax.json.bind.annotation.JsonbProperty;

public class LoginResponse {
    @JsonbProperty("user")
    private final String user;
    @JsonbProperty("token")
    private final String token;

    public LoginResponse(String user, String token) {
        this.user = user;
        this.token = token;
    }
}
