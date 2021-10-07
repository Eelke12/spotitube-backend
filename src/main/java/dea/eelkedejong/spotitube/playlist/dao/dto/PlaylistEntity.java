package dea.eelkedejong.spotitube.playlist.dao.dto;

public class PlaylistEntity {
    private int id;
    private String naam;
    private int eigenaarId;

    public PlaylistEntity(int id, String naam, int eigenaarId) {
        this.id = id;
        this.naam = naam;
        this.eigenaarId = eigenaarId;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getEigenaarId() {
        return eigenaarId;
    }
}
