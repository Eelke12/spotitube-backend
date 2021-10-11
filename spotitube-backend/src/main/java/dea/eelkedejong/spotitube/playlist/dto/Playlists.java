package dea.eelkedejong.spotitube.playlist.dto;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class Playlists {
    @JsonbProperty("playlists")
    private List<Playlist> playlists;
    @JsonbProperty("length")
    private int length;

    public Playlists(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
