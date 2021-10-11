package dea.eelkedejong.spotitube.eigenaar.dto;

import javax.json.bind.annotation.JsonbProperty;

public class LoginRequest {
    @JsonbProperty("user")
    private String gebruikersnaam;
    @JsonbProperty("password")
    private String wachtwoord;

    public LoginRequest() {
    }

    public LoginRequest(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
