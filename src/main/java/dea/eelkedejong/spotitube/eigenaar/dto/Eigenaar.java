package dea.eelkedejong.spotitube.eigenaar.dto;

public class Eigenaar {
    private final int id;
    private final String gebruikersnaam;
    private final String wachtwoord;
    private final String token;

    public Eigenaar(int id, String gebruikersnaam, String wachtwoord, String token) {
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.token = token;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public int getId() {
        return id;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
