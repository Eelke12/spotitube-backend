package dea.eelkedejong.spotitube.playlist.dto;

import dea.eelkedejong.spotitube.track.dto.Track;
import dea.eelkedejong.spotitube.track.dto.Tracks;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class Playlist {
    @JsonbProperty("id")
    private int id;
    @JsonbProperty("name")
    private String naam;
    @JsonbProperty("owner")
    private boolean owner;
    @JsonbProperty("tracks")
    private List<Track> tracks;

    public Playlist() {
    }

    public Playlist(int id, String naam, boolean owner, List<Track> tracks) {
        this.id = id;
        this.naam = naam;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public boolean isOwner() {
        return owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
