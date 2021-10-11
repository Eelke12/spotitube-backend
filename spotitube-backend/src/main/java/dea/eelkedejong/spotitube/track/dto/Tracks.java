package dea.eelkedejong.spotitube.track.dto;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class Tracks {
    @JsonbProperty("tracks")
    private List<Track> tracks;

    public Tracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
