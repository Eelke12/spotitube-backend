package dea.eelkedejong.spotitube.track.dto;

import javax.json.bind.annotation.JsonbProperty;

public class Track {
    @JsonbProperty("id")
    private int id;
    @JsonbProperty("title")
    private String title;
    @JsonbProperty("performer")
    private String performer;
    @JsonbProperty("duration")
    private int duration;
    @JsonbProperty("album")
    private String album;
    @JsonbProperty("playcount")
    private int playcount;
    @JsonbProperty("publicationDate")
    private String publicationDate;
    @JsonbProperty("description")
    private String description;
    @JsonbProperty("offlineAvailable")
    private boolean offlineAvailable;

    public Track() {
    }

    public Track(int id,
                 String title,
                 String performer,
                 int duration, String album,
                 int playcount,
                 String publicationDate,
                 String description,
                 boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPerformer() {
        return performer;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
}
